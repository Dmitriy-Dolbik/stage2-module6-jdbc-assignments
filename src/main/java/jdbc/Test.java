package jdbc;

public class Test
{
    public static void main(String[] args)
    {
        SimpleJDBCRepository simpleJDBCRepository = new SimpleJDBCRepository();
        User createdUser = new User();
        createdUser.setFirstName("TEST1");
        createdUser.setLastName("TEST1");
        createdUser.setAge(34);


        //System.out.println(simpleJDBCRepository.createUser(user));
        //System.out.println(simpleJDBCRepository.findUserById(1l));
        //System.out.println(simpleJDBCRepository.findUserByName("Jack"));
        //System.out.println(simpleJDBCRepository.findAllUser());
        //System.out.println(simpleJDBCRepository.updateUser(new User(1l,"Update", "Update", 3)));

    }
}
