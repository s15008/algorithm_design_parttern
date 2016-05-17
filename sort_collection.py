#!/usr/bin/python3
# -*- coding: utf-8 -*-

LIST_COUNT = 1000
LOOP_COUNT = 500
MAX_NUM = 10000

def data_generate():
    import random
    return [ random.randint( 1, MAX_NUM) for _ in range( LIST_COUNT)]

def quick_sort( data):
    '''
    適当な基準値を決めて、配列の要素を基準値より大きいグループと小さいグループに分割することを繰り返す
    ベーシックな方法
    くそなことに、今のところ一番はやい
    '''
    if len( data) < 1:
        return data

    pivot = data[ 0]
    left = []
    right = []
    for x in range( 1, len( data)):
        if data[ x] <= pivot:
            left.append( data[ x])
        else:
            right.append( data[ x])

    left = quick_sort( left)
    right = quick_sort( right)

    return left + [pivot] + right

def quick_sortEx( data):
    '''
    適当な基準値を決めて、配列の要素を基準値より大きいグループと小さいグループに分割することを繰り返す
    配列の1番目と2番目を比較して大きい方を選択する方法
    '''
    if len( data) < 1:
        return data

    pivot = data[ 0]

    # 配列の1番目と2番目を比較して大きい方を選択する
    if len( data) > 2:
        pivot = data[ 0] if data[ 0] < data[ 1] else data[ 1]

    left = []
    right = []
    for x in range( 1, len( data)):
        if data[ x] <= pivot:
            left.append( data[ x])
        else:
            right.append( data[ x])

    left = quick_sortEx( left)
    right = quick_sortEx( right)

    return left + [pivot] + right

def quick_sortEx2( data):
    '''
    適当な基準値を決めて、配列の要素を基準値より大きいグループと小さいグループに分割することを繰り返す
    配列から3点比較して中間値を選択する方法
    '''
    if len( data) < 1:
        return data

    pivot = data[ 0]

    # 配列から3点比較して中間値を選択する
    if len( data) > 3:
        pivot = data[ 2] if pivot > data[ 2] else pivot
        pivot = data[ 0] if pivot < data[ 0] else pivot


    left = []
    right = []
    for x in range( 1, len( data)):
        if data[ x] <= pivot:
            left.append( data[ x])
        else:
            right.append( data[ x])

    left = quick_sortEx2( left)
    right = quick_sortEx2( right)

    return left + [pivot] + right

if __name__ == '__main__':
    import time
    import sys

    # quick_sort TEST
    start = time.time()

    for _ in range( LOOP_COUNT):
        data = data_generate()
        quick_sort( data)
        print( '.', end='') if (_+1) % ( LOOP_COUNT // 10) != 0 else print( str(_ // ( LOOP_COUNT // 10) + 1) + "0%" )
        sys.stdout.flush()

    end = time.time()
    print( 'quick_sort 経過時間:', ( end - start))
    print( '           平均時間:', ( end - start) / LOOP_COUNT)

    # quick_sortEx TEST
    start = time.time()

    for _ in range( LOOP_COUNT):
        data = data_generate()
        quick_sortEx( data)
        print( '.', end='') if ( _+1) % ( LOOP_COUNT // 10) != 0 else print( str(_ // ( LOOP_COUNT // 10) + 1) + "0%" )
        sys.stdout.flush()

    end = time.time()
    print( 'quick_sortEx 経過時間:', ( end - start))
    print( '             平均時間:', ( end - start) / LOOP_COUNT)

    # quick_sortEx2 TEST
    start = time.time()

    for _ in range( LOOP_COUNT):
        data = data_generate()
        quick_sortEx2( data)
        print( '.', end='') if ( _+1) % ( LOOP_COUNT // 10) != 0 else print( str(_ // ( LOOP_COUNT // 10) + 1) + "0%" )
        sys.stdout.flush()

    end = time.time()
    print( 'quick_sortEx2 経過時間:', ( end - start))
    print( '              平均時間:', ( end - start) / LOOP_COUNT)
