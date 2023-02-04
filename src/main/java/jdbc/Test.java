package jdbc;

public class Test
{
    public static void main(String[] args)
    {
        SimpleJDBCRepository simpleJDBCRepository = new SimpleJDBCRepository();
        User createdUser = new User();
        createdUser.setFirstName("TEST6");
        createdUser.setLastName("TEST6");
        createdUser.setAge(34);
        System.out.println(simpleJDBCRepository.createUser(createdUser));
        //System.out.println(simpleJDBCRepository.findUserById(3l));
        //System.out.println(simpleJDBCRepository.findUserByName("TEST3"));
        //System.out.println(simpleJDBCRepository.findAllUser());
        //System.out.println(simpleJDBCRepository.updateUser(new User(2l,"Update", "Update", 3)));
        //simpleJDBCRepository.deleteUser(4l);

    }
}
