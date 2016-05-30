#!/usr/bin/python3
# -*- encoding: utf-8 -*-

import time
import random



def junken1():

    # じゃんけんの手
    GU = 1
    CHOKI = 2
    PA = 3

    DRAW = 1
    WIN = 2
    LOSE = 3

    # ユーザーが手を選ぶ
    #user = int( input( "ユーザーの手 = "))
    user = random.randint( 1, 3)

    # コンピュータが手を選ぶ
    computer = random.randint( 1, 3)
    #print( "コンピュータの手 = ", computer)

    # 勝敗を判定する
    judge = -1
    if user == GU and computer == GU: judge = DRAW
    elif user == GU and computer == CHOKI: judge = WIN
    elif user == GU and computer == PA: judge = LOSE
    elif user == CHOKI and computer == GU: judge = LOSE
    elif user == CHOKI and computer == CHOKI: judge = DRAW
    elif user == CHOKI and computer == PA: judge = WIN
    elif user == PA and computer == GU: judge = WIN
    elif user == PA and computer == CHOKI: judge = LOSE
    elif user == PA and computer == PA: judge = DRAW

    # 結果を表示する
    '''
    if judge == DRAW: print( "DRAW" )
    elif judge == WIN: print( "WIN" )
    else: print( "LOSE" )
    '''

def junken2():

    # じゃんけんの手
    GU = 1
    CHOKI = 2
    PA = 3

    DRAW = 1
    WIN = 2
    LOSE = 3

    # ユーザーが手を選ぶ
    #user = int( input( "ユーザーの手 = "))
    user = random.randint( 1, 3)

    # コンピュータが手を選ぶ
    #print( "コンピュータの手 = ", computer)
    computer = random.randint( 1, 3)

    # 勝敗を判定する
    if user == computer: judge = DRAW
    elif ( user == GU and computer == CHOKI) or\
         ( user == CHOKI and computer == PA) or\
         ( user == PA and computer == GU):
         judge = WIN
    else: judge = LOSE

    # 結果を表示する
    '''
    if judge == DRAW: print( "DRAW" )
    elif judge == WIN: print( "WIN" )
    else: print( "LOSE" )
    '''

def junken3():

    # じゃんけんの手
    GU = 1
    CHOKI = 2
    PA = 3

    DRAW = 1
    WIN = 2
    LOSE = 3

    # ユーザーが手を選ぶ
    #user = int( input( "ユーザーの手 = "))
    user = random.randint( 1, 3)

    # コンピュータが手を選ぶ
    #print( "コンピュータの手 = ", computer)
    computer = random.randint( 1, 3)

    # 勝敗を判定する
    judge = ( user - computer + 3) % 3

    # 結果を表示する
    '''
    if judge == DRAW: print( "DRAW" )
    elif judge == WIN: print( "WIN" )
    else: print( "LOSE" )
    '''

def junken4():

    # じゃんけんの手
    GU = 1
    CHOKI = 2
    PA = 3

    DRAW = 1
    WIN = 2
    LOSE = 3

    judge_list = [
                [ DRAW, WIN, LOSE ],
                [ LOSE, DRAW, WIN ],
                [ WIN, LOSE, DRAW ],
            ]

    # ユーザーが手を選ぶ
    #user = int( input( "ユーザーの手 = "))
    user = random.randint( 1, 3)

    # コンピュータが手を選ぶ
    #print( "コンピュータの手 = ", computer)
    computer = random.randint( 1, 3)

    # 勝敗を判定する
    judge =  judge_list[ user - 1][ computer - 1]

    # 結果を表示する
    '''
    if judge == DRAW: print( "DRAW" )
    elif judge == WIN: print( "WIN" )
    else: print( "LOSE" )
    '''

'''
スピードテスト
@param fnc 速度を図るファンクション
@param msg 表示する文字列
'''
def speed_test(fnc, loop_count, msg):
    print(msg)
    start = time.time()
    for x in range(loop_count):
        fnc()
    end = time.time()
    print('経過時間 : ', end - start)
    print('-------------------------------------')

'''
メインファンクション
'''
LOOP_COUNT = 100000

def main():
    speed_test(junken1, LOOP_COUNT, "普通のif文パターン")
    speed_test(junken2, LOOP_COUNT, "3通りのパターン")
    speed_test(junken3, LOOP_COUNT, "計算式パターン")
    speed_test(junken4, LOOP_COUNT, "リスト表パターン")

if __name__ == '__main__':
    main()

