class Student3
{
	int id;
	String name;
	void display()
	{
		System.out.println(id+11+name + "Akhil");
		System.out.println(id +12 +name + "Abhin");
	}
	public static void main(String args[])
	{
		Student3 s1=new Student3();
		Student3 s2=new Student3();
		s1.display();
		s2.display();
 	}
}