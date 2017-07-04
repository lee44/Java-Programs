//Joshua Lee

public class Student implements Comparable<Student>
	{
		int id;
		String name;
		String school;
		
		public Student(int id,String name, String school)
		{
			this.id=id;
			this.name=name;
			this.school=school;
		}
		
		public int getId()
		{
			return id;
		}
		
		public String getName()
		{
			return name;
		}
		
		public String getSchool()
		{
			return school;
		}
		 
		public int compareTo(Student o) 
		{
			if(getId()>(o.getId()))
				return 1;
			else if(getId()<(o.getId()))
				return -1;
			else
				return 0;
		}
		
		public boolean equals(Object o)
		{
			if(o instanceof Student)
				return getId()==((Student)o).getId();
			
				return false;
		}
		
		public String toString()
		{
			return ""+getName()+","+getId()+","+getSchool()+"\n";
		}
	}