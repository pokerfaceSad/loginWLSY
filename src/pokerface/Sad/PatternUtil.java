package pokerface.Sad;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {

	public static void getSelected(String html){
		
		//匹配姓名
		String nameReg = "<div align=\"center\">[\\s\\S]+?([\u4e00-\u9fa5]+)[\\s\\S]+?同学，";
		Pattern P = Pattern.compile(nameReg);
		Matcher m = P.matcher(html);
		if(m.find())
			System.out.println(m.group(1));
		//序号  实验项目 实验周次  实验时间 实验日期 上课教室 讲义出处 实验成绩 归一成绩 备注
//		String msgReg = "<th class=\"tableHeaderText\" align=\"left\" height=\"25\" width=\"15\">(.+?)</th><th class=\"tableHeaderText\" align=\"left\" height=\"25\" width=\"280\">(.+?)</th><th class=\"tableHeaderText\" align=\"left\" height=\"25\" width=\"30\">(.+?)</th><th class=\"tableHeaderText\" align=\"left\" height=\"25\" width=\"100\">(.+?)</th><th class=\"tableHeaderText\" align=\"left\" height=\"25\" width=\"80\">(.+?)</th><th class=\"tableHeaderText\" align=\"left\" height=\"25\" width=\"35\">(.+?)</th><th class=\"tableHeaderText\" align=\"center\" height=\"25\" width=\"80\">(.+?)</th><th class=\"tableHeaderText\" align=\"center\" height=\"25\" width=\"70\">(.+?)</th><th class=\"tableHeaderText\" align=\"center\" height=\"25\" width=\"35\">(.+?)</th><th class=\"tableHeaderText\" align=\"center\" height=\"25\">(.+?)</th>";
//		P = Pattern.compile(msgReg);
//		m = P.matcher(html);
		String Reg = "<td class=\"forumRow\" height=\"25\"><span>(.+?)</span></td><td class=\"forumRow\" height=\"25\"><a class=\"linkSmallBold\" target=\"_new\">(.+?)</a></td><td class=\"forumRow\" align=\"center\" height=\"25\"><span>(.+?)</span></td><td class=\"forumRow\" height=\"25\"><span>(.+?)</span></td><td class=\"forumRow\" height=\"25\"><span>(.+?)</span></td><td class=\"forumRow\" align=\"center\" height=\"25\"><span>(.+?)</span></td><td class=\"forumRow\" height=\"25\"><a class=\"linkSmallBold\" target=\"_new\">(.+?)</a></td><td class=\"forumRow\" height=\"25\"><span>(.+?)</span></td><td class=\"forumRow\" height=\"25\"><span>(.*?)</span></td><td class=\"forumRow\" height=\"25\"><span>(.+?)</span></td>"; 
		P = Pattern.compile(Reg);
		m = P.matcher(html);
		while(m.find())
		{
			for(int i=1;i<=m.groupCount();i++)
			{
				System.out.print(m.group(i)+"      ");
			}
			System.out.println();
		}
	}
}
