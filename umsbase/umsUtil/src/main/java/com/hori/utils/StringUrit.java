package com.hori.utils;

import java.util.Random;

public class StringUrit {
	
	static   long   seed   =   System.currentTimeMillis();   
	static   long   skip   =   Long.parseLong("187649984473770");   
	static   char   ch[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',};  

	
	public static  String getString(int   length){   
		if(length>62   ||   length<=0){   
			throw   new   IllegalArgumentException();   
		}else   if(length   ==   62){   
			length--;   
		}	   

		Random   r   =   new   Random(seed);   
		int   rNum;   
		char   temp;	   
		for(int   i=0;   i<length;   i++){   
			rNum   =   r.nextInt(62);   
			seed   +=   skip;   
			r.setSeed(seed);   
			if(rNum   <   i){   
				rNum   =   i+1;   
			}   
			temp   =   ch[i];   
			ch[i]   =   ch[rNum];   
			ch[rNum]   =   temp;	   
		}   
		return   new   String(ch,0,length);   

	}
}
