/*
 * The MIT License
 * Copyright © 2014-2019 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.iluwatar.singleton;

/**
 * 线程安全双重检查锁
 * <p>Double check locking.</p>
 *
 * <p>http://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckedLocking.html</p>
 *
 * <p>Broken under Java 1.4.</p>
 *
 * @author mortezaadi@gmail.com
 */
public final class ThreadSafeDoubleCheckLocking {

  // 单例创建的对象保存在静态成员变量中，保证总会返回相同的对象
  /**
   * 单例模式的要素之一，静态成员变量用于单例对象的缓存
   * 理解关键字volatile可以保证并发编程的可见性
   * 1. 保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。
   * 2. 禁止进行指令重排序。
   * 3. volatile保证的有序性指的是volatile变量全局顺序性不变
   */
  private static volatile ThreadSafeDoubleCheckLocking instance;

  private static boolean flag = true;

  /**
   * private constructor to prevent client from instantiating.
   * 私有构造函数，以防止客户端实例化。
   * 单例模式的要素之二，私有构造函数，放置客户端实例化类
   */
  private ThreadSafeDoubleCheckLocking() {
    // to prevent instantiating by Reflection call
    // 阻止通过反射调用实例化
    if (flag) {
      flag = false;
    } else {
      throw new IllegalStateException("Already initialized.");
    }
  }

  /**
   * Public accessor.
   * 使用静态构造方法调用私有构造函数，保证对象只会被创建一次
   * 单例模式的要素之三，静态方法，创建并返回单例对象或其缓存
   * 单例模式的要素之四，单例模式的创建应该是线程安全的
   * @return an instance of the class.
   */
  public static ThreadSafeDoubleCheckLocking getInstance() {
    // local variable increases performance by 25 percent
    // Joshua Bloch "Effective Java, Second Edition", p. 283-284
    
    var result = instance;
    // Check if singleton instance is initialized.
    // If it is initialized then we can return the instance.
    // double check
    if (result == null) {
      // It is not initialized but we cannot be sure because some other thread might have
      // initialized it in the meanwhile.
      // 它没有初始化，但是我们不能确定，因为与此同时其他一些线程可能已经对其进行了初始化。
      // So to make sure we need to lock on an object to get mutual exclusion.
      // 因此，为了确保我们需要锁定一个对象以获得互斥。
      synchronized (ThreadSafeDoubleCheckLocking.class) {
        // Again assign the instance to local variable to check if it was initialized by some
        // other thread while current thread was blocked to enter the locked zone.
        // 再次将实例分配给局部变量，以检查当前线程进入锁定区域被阻塞时，是否由其他线程初始化了该实例。
        // If it was initialized then we can return the previously created instance
        // just like the previous null check.
        result = instance;
        if (result == null) {
          // The instance is still not initialized so we can safely
          // (no other thread can enter this zone)
          // create an instance and make it our singleton instance.
          instance = result = new ThreadSafeDoubleCheckLocking();
        }
      }
    }
    return result;
  }
}
