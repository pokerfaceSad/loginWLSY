package pokerface.Sad;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupUtil {
	
	static void getMsg(String html){
		
		Document doc = Jsoup.parse(html);
		Elements ele = doc.select("div[align=center]");
		String div = ele.get(0).toString();
		//字符串操作得到姓名
		System.out.println(div.split("\n")[1].replace(" 同学，", "").replace(" ", ""));
		Elements eles = doc.select("table[id=Orders_ctl00]").get(0).select("tr");
		Element[] trs = new Element[eles.size()];
		eles.toArray(trs); 
		Object[] objs = trs[0].select("th").toArray();
		for(Object obj:objs)
		{
			System.out.println(((Element) obj).text());
		}
		Element[] tds = new Element[20];
		Elements temp = null;
		String[] properties = new String[10];
		Element td = null;
		Element tr = null;
		Experiment[] experiments = new Experiment[trs.length-1]; 
		for (int i=0;i<trs.length-1;i++) {
			tr = trs[i+1];
			temp = tr.select("td");
			temp.toArray(tds);
			for(int j=0;j<tds.length;j++)
			{
				td = tds[j];
				if(td!=null)
				{
					System.out.println(td.text());
					properties[j] = td.text();
				}
			}
			experiments[i] = new Experiment(properties);
		}
		
		ObjectOutputStream oos = null;	
	try {
		 oos = new ObjectOutputStream(new FileOutputStream("experiments"));
		oos.writeObject(experiments);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}finally{
		
		if(oos!=null)
		{
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	}
}
class Experiment implements Serializable{
	
	String No;	 //序号
	String Name; //实验项目
	String Week; //实验周次
	String Time; //实验时间
	String Date; //实验日期
	String Classroom; //上课教室
	String Notes;	//讲义出处
	String Score;	//实验成绩
	String Result;	//最终成绩
	String Mark;	//备注
	
	public Experiment(){}
	public Experiment(String[] properties) {
		No = properties[0];
		Name =  properties[1];
		Week =  properties[2];
		Time =  properties[3];
		Date =  properties[4];
		Classroom =  properties[5];
		Notes =  properties[6];
		Score =  properties[7];
		Result =  properties[8];
	   	Mark =  properties[9];
	}
	@Override
	public String toString() {
		return "Experiment [No=" + No + ", Name=" + Name + ", Week=" + Week
				+ ", Time=" + Time + ", Date=" + Date + ", Classroom="
				+ Classroom + ", Notes=" + Notes + ", Score=" + Score
				+ ", Result=" + Result + ", Mark=" + Mark + "]";
	}

}