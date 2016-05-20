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
 * 对线程的wait, sleep, notifyAll的学习。wait, notify机制通常用于等待机制的实现。当条件未满足时调用wait进入等待状
 * 态, 一旦条件满足,调用notify或notifyAll唤醒等待的线程继续执行。
 * <p>
 * Created by on 16/5/19.
 *
 * @author Jackie Zhu
 * @version 1.0
 */
public class WaitThread extends Thread {
    private static final String TAG = "WaitThread";

    // 用于等待、唤醒对象
    private static Object sLockObject = new Object();

    static void waitAndNotifyAll() {
        Log.d(TAG, "waitAndNotifyAll: 主线线程运行");
        // 创建并启动子线程
        Thread thread = new WaitThread();
        thread.start();
        long startTime = System.currentTimeMillis();
        synchronized (sLockObject) {
            try {
                Log.d(TAG, "waitAndNotifyAll: 主线程等待");
                sLockObject.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long timeMs = System.currentTimeMillis() - startTime;
        Log.d(TAG, "waitAndNotifyAll: 等待耗时 = " + timeMs + " 毫秒");
    }

    @Override
    public void run() {
        super.run();
        try {
            synchronized (sLockObject) {
                Thread.sleep(3000);
                sLockObject.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
