
class Person{  
public int id;  
public String name;  
Person(int id,String name){  
this.id=id;  
this.name=name;  
}  
}  


class Emp extends Person{  
float salary;  
Emp(int id,String name,float salary){  
super(id,name);//reusing parent constructor  
this.salary=salary;  
}  
void display(){System.out.println(id+" "+name+" "+salary);}  
}  

class SuperClass{  
public static void main(String[] args){  
Emp e1=new Emp(1,"chakri",45000F);  
e1.display();  
}}  