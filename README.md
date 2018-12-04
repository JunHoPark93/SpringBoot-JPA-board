# SpringBoot-JPA-board
Springboot와 JPA를 이용해 CRUD 페이지 만들어보기


### Entitity Annotation

```java

@Entity
@Table(name = "mydata")
public class MyData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column(length = 50, nullable = false)
    private String name;

```

@Entity
엔티티 클래스라는 것을 나타낸다.

@Table
테이블명을 설정할 수 있는데 없어도 그 이름으로 생긴다.

@Id
pk정의, 반드시 설정해야 하는 값

GenerationType.AUTO는 자동으로 값을 할당한다.

### Repository

```java
@Repository
public interface MyDataRepository extends JpaRepository<MyData, Long> {
}

```
인터페이스만 정의해 놓고 코드를 작성하지않는다. Spring MVC에 의해 익명 클래스가 생성되기 때문이다.
Spring이 @Repository가 붙은 인터페이스를 bean으로 등록해 클래스를 만들어 준다.

```java
Iterable<MyData> list = myDataRepository.findAll();
```
컨트롤러에서 findAll()로 데이터를 가져온다. Iterable을 반환하는데 
Iterable이란 Implementing this interface allows an object to be the target of the "for-each loop" statement.
foreach문을 돌릴수 있게 만드는 인터페이스이고 많은 자료구조들이 (ArrayList, LinkedList 등) 이것을 구현하고 있다.  



참고) JpaRepository 는 CrudRepository보다 조금 더 확장된 기능을 갖는다.
 

saveAndFlush

@Transaction(readOnly=false)

 
 
 
 
 