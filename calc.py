#!/usr/bin/python3
# -*- coding: utf-8 -*-

class Calc:
    def __init__(self):
        (self.ST_INIT, self.ST_VAL1, self.ST_OP, self.ST_VAL2, self.ST_OK, self.ST_NG) = range(6)

    def isNumeric(self, c):
        if len(c) != 1: return False
        return '0' <= c <= '9'

    def isOperator(self, c):
        if len(c) != 1: return False
        return c in ('+', '-', '*', '/')

    def calculate(self, formula):
        pos = 0
        state = self.ST_INIT
        val1 = 0
        val2 = 0
        op = 0

        while (state != self.ST_OK and state != self.ST_NG):
            if state == self.ST_INIT:
                print("現在の状態 = 初期\t取り出した文字 =", formula[pos])
                if self.isNumeric(formula[pos]):
                    val1 = int(formula[pos])
                    state = self.ST_VAL1
                else:
                    state = self.ST_NG

            elif state == self.ST_VAL1:
                print("現在の状態 = 値1\t取り出した文字 =", formula[pos])
                if self.isNumeric(formula[pos]):
                    val1 = val1 * 10 + int(formula[pos])
                elif self.isOperator(formula[pos]):
                    op = formula[pos]
                    state = self.ST_OP
                else:
                    state = self.ST_NG

            elif state == self.ST_OP:
                print("現在の状態 = 演算子\t取り出した文字 =", formula[pos])
                if self.isNumeric(formula[pos]):
                    val2 = int(formula[pos])
                    state = self.ST_VAL2
                else:
                    state = self.ST_NG

            elif state == self.ST_VAL2:
                try:
                    print("現在の状態 = 値2\t取り出した文字 =", formula[pos])
                    if self.isNumeric(formula[pos]):
                        val2 = val2 * 10 + int(formula[pos])
                    else:
                        state = self.ST_NG
                except IndexError:
                    state = self.ST_OK

            pos+=1

        if state == self.ST_OK:
            print("現在の状態 = 数式OK\n")
            print("値1 = {}".format(val1))
            print("演算子 = {}".format(op))
            print("値2 = {}".format(val2))

            result = 0
            if op == "+":
                result = val1 + val2
            elif op == "-":
                result = val1 - val2
            elif op == "*":
                result = val1 * val2
            elif op == "/":
                result = val1 / val2

            print("計算結果 = {}".format(result))

        else:
            print("現在の状態 = 数式NG\n")
            print("数式に誤りがあります。")

'''
メインファンクション
'''
INPUT_DATA = "1234+5678"

def main():
    Calc().calculate(INPUT_DATA)

if __name__ == '__main__':
    main()

