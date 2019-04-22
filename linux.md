在文件各行首追加内容
sed "s/^/content/g" file

在文件各行尾追加内容
sed "s/$/content/g" file


查看系统编码
locale

系统编码文件
/etc/sysconfig/i18n
比将英文编码改为中文编码 只需将LANG=en_US.UTF-8改为 LANG=zh_CN.UTF-8

转换文件编码
iconv -f utf8  -t GB18030  file1 > file2

查看文件或命令所在的绝对路径
whereis [target] 会把所有包含target的路径都列举出来
which target 	仅查看当前要执行的target命令的路径


查看机器域名 hostname
查看机器ip hostname -i
查看ip对应域名   host ip
查看域名对应的ip地址：ping 域名      or    nslookup 域名

#linux命令
find 搜索范围路径 -name '目标文件'  在文件中查找目标
find 搜索范围路径 -name '目标文件' -delete 删除找到的文件


xargs 从stdin（手动输入/cat/find得到的数据等）中获取数据格式化后为其他命令提供参数，比如xargs ls -l 然后手动输入文件名来查看文件属性；cat file |xargs 来用一行查看文件；cat file |xargs -n number 查看文件，每行number个参数；cat file |xargs -d 'delimiter' 分割显示；
find ./ -name  'key'|xargs command 对查找到的文件执行command命令，但可能因为数据重新格式化导致达不到效果，可以使用 find ./ -name 'key' -print0 |xargs -0 command，其中-print0表示以'\0'结束文件名，xargs -0表示以null作为分隔符。
