#!/usr/bin/python
import re
import glob
import zipfile
import zlib
import zipfile
import os
import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.image import MIMEImage
import mimetypes
from argparse import ArgumentParser
from email import encoders
from email.message import Message
from email.mime.audio import MIMEAudio
from email.mime.base import MIMEBase
from email.mime.image import MIMEImage
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
import logging
import logging.handlers
import sys
import TesterMail

zf = zipfile.ZipFile('CSC344.zip', 'w')



def makeStuff(path,progName,list1):
	l = list1;
	count = 0;
	os.chdir(path)
	prog = progName;
	thing = open(prog)
	for line in thing:
		r = re.findall(r"[_a-zA-Z][_a-zA-Z0-9\-]*",line)
		for k in r:
	    		if(l.count(k) == 0):
	    			l.append(k)
	temp = open(prog + "_Idents.txt",  "w")
	for w in l:
		temp.write(prog +","+w+"\n")

	temp.close()
	thing.close()
	zf.write(prog)
	zf.write(prog + "_Idents.txt")








makeStuff("C:/Users/Dean/Desktop/CSC344/C",'FinalProject1.c')
makeStuff("C:/Users/Dean/Desktop/CSC344/LISP",'Project2.lisp')
# makeStuff("C:/Users/Dean/Desktop/CSC344/SCALA",)
makeStuff("C:/Users/Dean/Desktop/CSC344/PROLOG",'test.pl')
makeStuff("C:/Users/Dean/Desktop/CSC344/PYTHON",'test.py')


# sendTo = input("Send the email to: ")
# print(sendTo)
zf.close()
os.chdir("C:/Users/Dean/Desktop/CSC344/PYTHON")
server = smtplib.SMTP('smtp.gmail.com', 587)
server.starttls()
server.login("dtupper@oswego.edu", "ludden16")


#Send the mail
msg = MIMEMultipart()
msg['Subject'] = 'All The Programs'
# me == the sender's email address
# family = the list of all recipients' email addresses
msg['From'] = "dtupper@oswego.edu"
msg['To'] = "dtupper@oswego.edu"

part = MIMEBase('application', 'zip')
fp = open('CSC344.zip', 'rb')
part.set_payload(fp.read())
encoders.encode_base64(part)
part.add_header('Content-Disposition',
                'attachment; filename="%s"' % os.path.basename('CSC344.zip'))
msg.attach(part)


server.send_message(msg)
# l = list();
# count = 0;
# os.chdir("C:/Users/Dean/Desktop/CSC344/C")
# prog = 'FinalProject1.c';
# thing = open(prog)
# for line in thing:
# 	r = re.findall(r"[_a-zA-Z][_a-zA-Z0-9\-]*",line)
# 	for k in r:
#     		if(l.count(k) == 0):
#     			l.append(k)
# temp = open(prog + "_Idents.txt",  "w")
# for w in l:
# 	temp.write(w+"\n")

# temp.close()
# thing.close()
# zf.write(prog)
# zf.write(prog + "_Idents.txt")

# l = list();
# count = 0;
# os.chdir("C:/Users/Dean/Desktop/CSC344/LISP")
# prog = 'Project2.lisp';
# thing = open(prog)
# for line in thing:
# 	r = re.findall(r"[_a-zA-Z][_a-zA-Z0-9\-]*",line)
# 	for k in r:
#     		if(l.count(k) == 0):
#     			l.append(k)
# temp = open(prog + "_Idents.txt",  "w")
# for w in l:
# 	temp.write(w+"\n")

# temp.close()
# thing.close()
# zf.write(prog)
# zf.write(prog + "_Idents.txt")

# l = list();
# count = 0;
# os.chdir("C:/Users/Dean/Desktop/CSC344/LISP")
# prog = 'Project2.lisp';
# thing = open(prog)
# for line in thing:
# 	r = re.findall(r"[_a-zA-Z][_a-zA-Z0-9\-]*",line)
# 	for k in r:
#     		if(l.count(k) == 0):
#     			l.append(k)
# temp = open(prog + "_Idents.txt",  "w")
# for w in l:
# 	temp.write(w+"\n")

# temp.close()
# thing.close()
# zf.write(prog)
# zf.write(prog + "_Idents.txt")

# l = list();
# count = 0;
# os.chdir("C:/Users/Dean/Desktop/CSC344/C")
# thing = open('FinalProject1.c')
# for line in thing:
# 	r = re.findall(r"[_a-zA-Z][_a-zA-Z0-9]*",line)
# 	for k in r:
#     		if(l.count(k) == 0):
#     			l.append(k)
# print(l)
# temp = open("foo.txt",  "w")
# for w in l:
# 	temp.write(w+"\n")

# zf.write('FinalProject1.c')


