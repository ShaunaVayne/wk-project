package com.redis.demo;

/**
 * @Author: WangKun
 * @Description:
 * @Date: Created in 2019/7/31 14:13
 * @ProjectName: ework
 * @Version: 1.0.0
 */
public class TransactionTest {

    static ThreadLocal<TransactionManager> t = new ThreadLocal<>();

    public static void main(String[] args) {
        //这个简单的模拟aop控制事务的过程,实际上更复杂
        A a = new ProxyA();
        a.a();
    }

    public static class A {
        B b = new ProxyB();

        public void a() {
            try{
                b.b();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public static class B {
        public void b() {
            throw new RuntimeException("抛出异常了");
        }
    }

    public static class ProxyA extends A {
        A a = new A();

        public void a() {
            getManager().begin();
            try {
                a.a();
                getManager().commit();
            } catch (Exception e) {
                getManager().rollback();
            }
        }
    }

    public static class ProxyB extends B {
        B b = new B();

        public void b() {
            getManager().begin();
            try {
                b.b();
            } catch (Exception e) {
                //实际代码并非这么简单,他要判断当前是否事务最外层,这里简单的标记回滚
                getManager().setRollbackOnly();
                throw e;
            }
        }
    }

    public static class TransactionManager {

        private boolean flag = false;

        public void begin() {

        };

        public void commit() {
            if(!flag){
                System.out.println("事务提交了");
            }else{
                //实际此时会抛出异常,你可以尝试在你的a方法里加一个增删改操作,估计也会抛异常
                System.out.println("事务已经标记回滚");
            }
        };

        public void rollback() {
            System.out.println("事务回滚了");
        };

        public void setRollbackOnly() {
            this.flag = true;
        }
    }

    public static TransactionManager getManager() {
        TransactionManager manager = t.get();
        if (manager == null) {
            t.set(new TransactionManager());
        }
        return t.get();
    }
}
