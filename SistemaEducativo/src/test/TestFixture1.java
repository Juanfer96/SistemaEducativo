package test;

import sistemaeducativo.Facultad;

public class TestFixture1
{
    Facultad facutltad;
    public TestFixture1()
    {
    }

    public void setUp()
    {
        this.facutltad=new Facultad();
    }

    public void tearDown()
    {
    }
}
