<?php

namespace App\Http\Controllers\API;

use Exception;
use App\Models\User;
use Illuminate\Http\Request;
use App\Helpers\ResponseFormatter;
use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Validator;
use App\Actions\Fortify\PasswordValidationRules;


class UserController extends Controller

{

    use PasswordValidationRules;

    public function login(Request $request)
    {
        try{
            // Validasi Input
            $request->validate([
                'email' =>'email|required',
                'password' => 'required'
            ]);

            // Mengecek credentials (login)

            $credentials = request(['email', 'password']);
            if(!Auth::attempt($credentials)) {
                //login gagal
                return ResponseFormatter::error([
                    'massage' => 'Unauthorized'
                ], 'Authentication Failed', 500);
            }

            // Jika hash tidak sesuai maka beri error
            $user = User:: where('email', $request->email)->first();
            if(!Hash::check($request -> password, $user->password, [])) {
                throw new \Exception('Invalid Credentials');
            }

            // Jika Berhasil Maka Loginkan
            $tokenResult = $user->createToken('authToken')->plainTextToken;
            return ResponseFormatter::success([
                'accessToken' => $tokenResult,
                'token_type' => 'Bearer',
                'user' => $user
            ], 'Authenticated');

        } catch(Exception $error) {
            return ResponseFormatter::error([
                'message' => 'Something went wrong',
                'error' => $error
            ], 'Authentication Failed', 500);

        }
    }

    public function register (Request $request)
    {
        // Validasi sudah selesai 
        try{
            $request->validate([
                'name' => ['required', 'string', 'max:255'],
                'email' => ['required', 'string', 'email', 'max:255', 'unique:users'],
                'password' => $this->passwordRules()
            ]);

                // pembuatan User
                User::create([
                    'name' => $request->name,
                    'email' => $request->email,
                    'address' => $request->address,
                    'houseNumber' => $request->houseNumber,
                    'phoneNumber' => $request->phoneNumber,
                    'city' => $request->city,
                    'password' => Hash::make($request->password)

                ]);
                // ambil data yang sudah dibuat yang di atas
                $user = User::where('email', $request->email)->first();
                // pengambilan token untuk login
                $tokenResult = $user->createToken('authToken')->plainTextToken;
                //mengembalikan token serta data user nya
                return ResponseFormatter::success([
                    'access_token' => $tokenResult,
                    'token_type'  => 'Bearer',
                    'user' => $user
                ]);
        // bila ada error
        } catch (Exception $error){
            return ResponseFormatter::error([
                'message' => 'Something went wrong',
                'error' => $error
            ], 'Authentication Failed', 500);
        }
    }

    public function logout (Request $request)
    {
        //logout keluar dari aplikasi
        $token =  $request->user()->currentAccessToken()->delete;

        return ResponseFormatter::success($token, 'Token Revoked');
    }

    public function fetch (Request $request )
    {
        return ResponseFormatter::success(
            $request->user(), 'Data profile user berhasil diambil'
        );
    }

    public function updateProfile(Request $request)
    {
        // ambil semua data 
        $data = $request->all();

        $user = Auth::user(); // mengarah ke tabel user
        $user->update($data); // di update kembali 'profile upated'

        return ResponseFormatter::success($user, 'Profile Updated');
    }

    public function updatePhoto(Request $request)
    {
        // Validasi kurang dari 2mg
        $validator = Validator::make($request->all(), [
            'file' => 'required|image|max:2048'
        ]);
        // Validasi gagal (error)
        if($validator->fails())
        {
            return ResponseFormatter::error(
                ['error' => $validator->errors()],
                'Update Photo File',
                401
            );
        }

        if($request->file('file'))
        {
            $file = $request->file->store('assets/user','public');// simpan foto ke database

            // Simpan Foto ke database (urlnya)
            $user = Auth::user();
            $user->profile_photo_path = $file;
            $user->update();

            return ResponseFormatter::success([$file], 'File Successfully uploaded');
        }
    }

}


