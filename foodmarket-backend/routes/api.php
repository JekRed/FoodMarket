<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\API\FoodController;
use App\Http\Controllers\API\UserController;
use App\Http\Controllers\API\MidtransController;
use App\Http\Controllers\API\TransactionController;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->group(function(){
    // diakses oleh user
    // sudah login
    Route::get('user', [UserController::class, 'fetch']);// mengambil data profile
    Route::post('user', [UserController::class, 'updateProfile']);// update profile
    Route::post('user/photo', [UserController::class, 'updatePhoto']);// update photo
    Route::post('logout', [UserController::class, 'logout']);// keluar

    Route::post('checkout', [TransactionController::class, 'checkout']);//checkout

    Route::get('transaction', [TransactionController::class, 'all']);// transaksi
    Route::post('transaction/(id)', [TransactionController::class, 'update']);

});
    // tidak diakses oleh user
    // kalo blm login
Route::post('login', [UserController::class, 'login']);
Route::post('register', [UserController::class, 'register']);

Route::get('food', [FoodController::class, 'all']);

Route::post('midtrans/callback', [MidtransController::class, 'callback']);
