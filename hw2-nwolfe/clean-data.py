import sys
import os
def debug(var):
	print var
	raw_input("Press Enter...")

genes = open("hw2-nwolfe.out").readlines()
#cutout = ["virus"]
cutout = []
replace = []
output = []
for g in genes:
	flag = True
	for r in replace:
		g.replace(r,"")
	for c in cutout:
		if c in g:
			flag = False
			#debug(g)
	s = g.split("|")
	if len(s[-1].strip()) <= 1:
		flag = False
	if flag:		
		output.append(g)

fixit = open("hw2-nwolfe-fix.out","w")
for o in output:
	fixit.write(o);
fixit.close()
		
