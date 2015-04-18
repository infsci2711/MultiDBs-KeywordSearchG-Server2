package edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.rest;

import java.io.IOException;
import java.lang.String;
import java.util.LinkedList;

import org.json.JSONException;

public class testmetastore {

	public static String[] prestoinfo() throws Exception, IOException{
		// TODO Auto-generated method stub
		String[] jj = JsonReader.metast();
		String tablename = null;
		String urlbase="http://54.174.80.167:7654/";
		
		int q = 0;
		//String[] obj=stringObject.split(",");
		int r = jj.length;
		int rr =0;
		int E=0;
		LinkedList<String> ui =new LinkedList<String>();
		
		for (int i=0;i<r;i++){
			String s =jj[i];
			String[] obj2=jj[i].split(",");
			String dbid=obj2[0];
			rr= obj2.length;
			//System.out.println(dbid);
			
			for (int j=1;j<rr;j++){
				//urlf= new String[r*rr];
				//urlfinal= new String[r][rr];
				String[][] urlfinal=new String[r][rr];
				String[] urlf = new String[r*rr];;
				tablename=obj2[j];
				urlfinal[i][j]=urlbase + "Query/" + dbid + "/" + tablename;
				String tem = "";
				tem = urlfinal[i][j];
				urlf[q]=tem;
				ui.add(tem);
				//System.out.println(urlf[q]);
				q++;
				//System.out.println(urlf[0]);
			}
			}
		int cc=ui.size();
		String[] qq=new String[cc];
		for (int y=0;y<cc;y++){
			qq[y] = ui.get(y);
			//System.out.println(qq[y]);
		}
		return qq;
		}
		
	}


