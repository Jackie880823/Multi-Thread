/*
 *    Copyright 2016 The Open Source Project of Jackie Zhu
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 *             $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
 *             $                                                   $
 *             $                       _oo0oo_                     $
 *             $                      o8888888o                    $
 *             $                      88" . "88                    $
 *             $                      (| -_- |)                    $
 *             $                      0\  =  /0                    $
 *             $                    ___/`-_-'\___                  $
 *             $                  .' \\|     |$ '.                 $
 *             $                 / \\|||  :  |||$ \                $
 *             $                / _||||| -:- |||||- \              $
 *             $               |   | \\\  -  $/ |   |              $
 *             $               | \_|  ''\- -/''  |_/ |             $
 *             $               \  .-\__  '-'  ___/-. /             $
 *             $             ___'. .'  /-_._-\  `. .'___           $
 *             $          ."" '<  `.___\_<|>_/___.' >' "".         $
 *             $         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       $
 *             $         \  \ `_.   \_ __\ /__ _/   .-` /  /       $
 *             $     =====`-.____`.___ \_____/___.-`___.-'=====    $
 *             $                       `=-_-='                     $
 *             $     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   $
 *             $                                                   $
 *             $          Buddha bless         Never BUG           $
 *             $                                                   $
 *             $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
 */

package com.jackie.multi_thread;

import android.util.Log;

/**
 * 对join的学习,join函数分阻塞当前线程,直到子线程执行完毕之后才继续执行
 * Created by on 16/5/19.
 *
 * @author Jackie Zhu
 * @version 1.0
 */
public class Worker extends Thread {
    private static final String TAG = "Worker";

    public static void joinDemo() {
        Worker worker1 = new Worker("worker1");
        Worker worker2 = new Worker("worker2");
        try {
            Log.d(TAG, "joinDemo: 启动线程1");
            worker1.start();
            // 调用worker1的join函数,主线程会阻塞直到worker1执行完成
            worker1.join();
            Log.d(TAG, "joinDemo: 启动线程2");
            worker2.start();
            worker2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "joinDemo: 主线程执行完毕");
    }

    public Worker(String threadName) {
        super(threadName);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "run: work in " + getName());
    }
}
