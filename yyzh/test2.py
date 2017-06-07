# -*- coding: utf-8 -*-
import pandas as pd
import time
import threading
from multiprocessing import Process

path = 'data/user_installedapps.csv'
in_app = pd.read_csv(path)

ad_path = 'data/ad.csv'
ad = pd.read_csv(ad_path)

c = in_app
total = len(in_app.index)
_in_app = in_app
section = 100
size = int(total / section)
app_index = []
in_apps = []
start_ = 0
end_ = size
sections = []
for i in range(section):
    en = _in_app[end_ - 1:end_]['userID'].values[0]
    temp = en
    while True:
        end_ += 1
        if end_ > total:
            end_ = total
            break
        en = _in_app[end_ - 1:end_]['userID'].values[0]
        if temp != en:
            end_ -= 1
            break
    print(start_, end_)
    sections.append((start_, end_))
    print('======')
    start_ = end_
    end_ += size
    if end_ > total:
        end_ = total
    pass
print(sections)
for start, end in sections:
    _app = _in_app[start:end]
    in_apps.append(_app)
    app_index.append((_in_app[start:start + 1]['userID'].values[0],
                      _in_app[end - 1:end]['userID'].values[0],
                      len(app_index)))
    pass


# for i in range(section):
#     t = i * size
#     print(t, t + 1)
#     start = _in_app[t:t + 1]['userID'].values[0]
#     t = (i + 1) * size
#     print(t - 1, t)
#     end = _in_app[t - 1:t]['userID'].values[0]
#     if i == section - 1:
#         end = _in_app[total - 1:total]['userID'].values[0]
#     print(start)
#     print(end)
#     print('=========')
#     _app = _in_app[i * size: (i + 1) * size]
#     in_apps.append(_app)
#     app_index.append((start, end, len(app_index)))


def index(num):
    for start, end, i in app_index:
        if start <= num <= end:
            return i

    return 0


# for index, userId in enumerate(userids):
#     cat_map = c[c['userID'] == userId]['appCategory'].value_counts()
#     for key in cat_map.keys():
#         col = 'c' + str(key)
#         train.at[index, col] = cat_map[key]
#     if index % 10 == 0:
#         print(index)
#     pass
#
# train.to_csv('train_cat_0.csv')
thread_size = 5
train_section = '0'
train_path = 'data/test_sample.csv'
train = pd.read_csv(train_path, iterator=True)
train_lens = int(338489 / thread_size)
trains = []
for i in range(thread_size):
    if i < thread_size -1:
        trains.append(train.get_chunk(train_lens))
    else:
        trains.append(train.get_chunk(338489 - (i - 1) * train_lens))


def get_app(userid):
    indx = index(userid)
    return in_apps[indx]


in_app = None

cat_app = dict(zip(ad['creativeID'].values, ad['appID'].values))


def run(i):
    map_time = 0
    train_time = 0
    _train = trains[i]
    _train['installed'] = 0
    userids = _train['userID']
    for ind, userId in enumerate(userids):
        ind += i * train_lens
        app = get_app(userId)
        st1 = time.time()
        _temp = app[app['userID'] == userId]
        appIDs = _temp['appID'].values
        app_cat = _temp['appCategory']
        cat_map = app_cat.value_counts()
        appId = cat_app[_train.at[ind, 'creativeID']]
        # cat_map = c[c['userID'] == userId]['appCategory'].value_counts()
        map_time += time.time() - st1
        st1 = time.time()
        if len(cat_map) and appId in appIDs:
            _train.at[ind, 'installed'] = 1
            pass
        for key in cat_map.keys():
            col = 'c' + str(key)
            _train.at[ind, col] = cat_map[key]
        train_time += time.time() - st1
        # print(index)
        if ind % 1000 == 0:
            print(i, ind)
        pass
    print('save ..' + 'test_cat_' + train_section + str(i) + '.csv')
    _train.to_csv('data/test_cat_' + train_section + str(i) + '.csv')


ts = []
for i in range(thread_size):
    t = threading.Thread(target=run, args=(i,))
    t.setDaemon(True)
    t.start()
    ts.append(t)

for _t in ts:
    _t.join()

# ts = []
# for i in range(thread_size):
#     t = Process(target=run, args=(i,))
#     t.start()
#     ts.append(t)
#
# for _t in ts:
#     _t.join()
