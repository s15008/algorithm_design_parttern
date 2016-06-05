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
    if len( data) < 2:
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

def quick_sortEx(data):
    '''ぼくがかんがえてないさいきょうのくいっくそーと改
    適当な基準値を決めて、配列の要素を基準値より大きいグループと小さいグループに分割することを繰り返す
    配列をシーケンシャル走査して1番目より大きい数があったらそれを基準値にする方法
    配列が同じ値しかなかったらそのままリターンする
    '''
    if len(data) < 2:
        return data

    # 配列の1番目と2番目を比較して大きい方を選択する
    length = len(data)
    pivot = data[0]
    i = 0
    same = 0
    while i < length:
        if pivot < data[i]:
            pivot = data[i]
            break
        if pivot == data[i]:
            same += 1
        i += 1
    if same == length:
        return data

    left = []
    right = []
    for x in range(0, length):
        if data[x] < pivot:
            left.append(data[x])
        else:
            right.append(data[x])

    left = quick_sortEx(left)
    right = quick_sortEx(right)

    return left + right

def check_speed(func, amount, msg):
    ''' ファンクション処理時間測定関数
    ファンクションを複数回処理した経過時間と平均時間を出力する
    Args:
        func (function): 対象の関数
        amount (int): 施行回数
        msg (str): 表示メッセージ
    Reterns:
        str: 経過時間と平均時間を表すフォーマットされた文字列
    '''
    start = time.time()
    for _ in range(amount):
        data = data_generate()
        func(data)

    end = time.time()

    return '{}\n経過時間:{}\n平均時間:{}\n'.format(msg, (end - start), (end - start) / amount)

if __name__ == '__main__':
    import time
    import sys

    MAX_NUM = 100
    print(check_speed(quick_sort, LOOP_COUNT, 'quick_sort'))
    print(check_speed(quick_sortEx, LOOP_COUNT, 'quick_sortEx(ぼくの)'))

