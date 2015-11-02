# -*- coding:utf-8 -*-
import urllib
import urllib2
import json
import os

tag = {
    1: '常用',
    2: '高兴',
    3: '卖萌',
    4: '震惊',
    5: '生气',
    6: '无奈',
    7: '晕',
    8: '道歉',
    9: '动物',
    10: '害羞',
    11: '哭',
    12: '么么哒',
    13: '睡啦',
    14: '再见',
    15: '傲娇',
    16: '吃货',
    17: '得意',
    18: '害怕',
    19: '囧',
    20: '赞',
    21: '难过',
    22: '贱',
    23: '其他'
}
def request(tag_id, page=1, recursive=True):	
    data = {'tag_id': tag_id, 'type': 'tag', 'page': page}
    req = urllib2.Request(url = 'http://pinyin.sogou.com/dict/ywz/ajax/make_list.php', data = urllib.urlencode(data))
    response = urllib2.urlopen(req)
    ret = response.read()
    retjson = json.loads(ret)	
    parse(tag_id, retjson)
    page = retjson['page']
    if page>1 and recursive:
        for p in range(2, page+1):
            request(tag_id, p, False) 		    
 	
def parse(tag_id, json):
    category = tag[tag_id]
    filename = './out/%s.json' % category
    fh = open(filename, 'a')
    for entry in json['data']:
	c = '{"tag":"%s", "entry": "%s"}\r\n' % (entry['tag'], entry['entry'])
        fh.write(c.encode('utf-8'))
    fh.close()
 
if __name__ == '__main__':
    os.system('rm ./out/*.json')
    for i in range(1, 24):
        request(i)  
