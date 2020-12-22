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

package com.iluwatar.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 既是组合接口也是容器，当LetterComposite的子类中children为空时，不产生递归调用
 * 组合接口
 * Composite interface.
 */
public abstract class LetterComposite {

  // 对于leeter子类来说，children是空的
  private final List<LetterComposite> children = new ArrayList<>();

  public void add(LetterComposite letter) {
    children.add(letter);
  }

  public int count() {
    return children.size();
  }

  protected void printThisBefore() {
  }

  protected void printThisAfter() {
  }

  /**
   * Print.
   * 当LetterComposite的子类中children为空时，不产生递归调用
   * children.forEach为递归调用，这里递归终止的条件(base case)是children的长度为空
   * 在LetterComposite的实现类中，只有Letter的children成员变量为空
   * 这里相当于是后序遍历
   */
  public void print() {
    printThisBefore();
    // LetterComposite::print 表示方法引用符
    // 匿名内部类、lambda表达式、方法引用符
    // 这一块产生递归调用
    children.forEach(LetterComposite::print);
    printThisAfter();
  }
}
