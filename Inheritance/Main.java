class A
{
	void display()
	{
		System.out.println("YO");
	}
}
interface B
{
	
		int a = 90;
	
}
interface C
{
	
		int b = 67;
	
}
class D extends A implements B,C
{
	void demo()
	{
		System.out.println("Clas"+a);
		System.out.println("uo"+b);
	}
}
class Main
{
	public static void main(Stirng args[])
	{
		Main d = new Main();
		d.demo();
		d.display();
	}
}