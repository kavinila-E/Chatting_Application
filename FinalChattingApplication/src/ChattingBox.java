import java.util.*;
public class ChattingBox {
	public static void main(String[]args) {
		System.out.println("welcome to chat bot in how can i help u:");
		while(true) {
		
		Scanner sc=new Scanner(System.in);
		String question=sc.nextLine();
		if(question.toString().contains("name"))
				{
			System.out.println("my name is bot chat");
				}
		else if(question.toString().contains("äge"))
		{
			System.out.println("I born just a moment");
			
		}
		else if(question.toString().contains("create"))
		{
			System.out.println("I was created by kavinila");
			
		}
		else if(question.toString().contains("fuck"))
		{
			System.exit(0);
			
		}
	}
	

}
}