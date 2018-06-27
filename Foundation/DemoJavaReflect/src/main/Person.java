package main;

public class Person implements Alive,Study<Boo,String> {

	@FruitName("hhe")
	private String id ;

	private String name ;

	public String age ;
	

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    /**
     * ¾²Ì¬·½·¨
     */
    public static void update(){

    }
	    
	@Override
	public void Say() {
		// TODO Auto-generated method stub

	}

	@Override
	public Boo GetBook(String book) {
		// TODO Auto-generated method stub
		return null;
	}

}
