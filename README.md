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
 

// 여기 설명 추가하시오~
saveAndFlush

@Transaction(readOnly=false)
데이터베이스의 트랜잭션을 관리한다. Consistency문제를 해결 하기 위한 것. readOnly는 읽기 전용인데 이것을 false로 주어 변경을 허가하는 트랜잭션으로 만든다. 사실 false가 default인것은 비밀이다.ㅎ 
 
 
** JPQL 사용시 주의할 점
1. Camel Case로 작성할 것
2. 함수명의 단어 나열 순서를 주의할 것 -> find[by ****][타 조건][OrderBy ...]
3. type주의! (Java의 autoboxing 안됨!)


### Custom Annotation 

```java
@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface Phone {
    String message() default "you should input a valid phone number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean onlyNumber() default false;
}
```
이런 식으로 custom한 annotation을 만들 수 있다. 각 기능을 살펴보자.

```java
@Documented
```
Java Document에 포함 시킨다.
```java
@Target
```
annotation을 적용할 위치를 설정한다.
ElementType.PACKAGE : 패키지 선언
ElementType.TYPE : 타입 선언
ElementType.ANNOTATION_TYPE : 어노테이션 타입 선언
ElementType.CONSTRUCTOR : 생성자 선언
ElementType.FIELD : 멤버 변수 선언
ElementType.LOCAL_VARIABLE : 지역 변수 선언
ElementType.METHOD : 메서드 선언
ElementType.PARAMETER : 전달인자 선언
ElementType.TYPE_PARAMETER : 전달인자 타입 선언
ElementType.TYPE_USE : 타입 선언 

```java
@Retention
```
어느 시점까지 annotation이 영향을 미칠지 설정한다.
RetentionPolicy.SOURCE : 컴파일 전까지만 유효. (컴파일 이후에는 사라짐)
RetentionPolicy.CLASS : 컴파일러가 클래스를 참조할 때까지 유효.
RetentionPolicy.RUNTIME : 컴파일 이후에도 JVM에 의해 계속 참조가 가능. (리플렉션 사용)

```java
@ReportAsSingleViolation
```
(report all violations as a single error) 하나의 에러로 반환하게 된다. 여러 개 따로따로 validate진행하려면 삭제해야 한다.
```java
@Constraint(validatedBy = PhoneValidator.class)
```
커스텀한 Validator class를 등록한다. 


@PersistenceContext
EntityManager entityManager

스프링 부트의 경우 EntityManager실행시에 자동으로 bean등록하는데 이 때 사용하는 annotation이 @PersistenceContext이다.


엔티티를 조회할때, 이미 영속 컨텍스트에서 관리되고 있는 엔티티인지 확인을 먼저합니다. 만일 관리되고 있는 엔티티일 경우 DB 조회 없이 1차캐시에서 해당 결과를 리턴해줍니다. 만일 아직 영속화 되지 않은 엔티티라면 DB에서 조회를 해와서 1차 캐시에 저장을 하며 해당 엔티티를 영속화 시킨 후 결과를 반환해 줍니다.


### Query
1. ?를 사용한 매개변수 쿼리
```java
String queryStr = "from MyData where id = ?1 or name like ?2 or mail like ?3";

Query query = entityManager.createQuery(queryStr).setParameter(1, fid)
                                .setParameter(2, "%" + str + "%")
                                .setParameter(3, str + "@%");

```

2. Named Query

```java
@Entity
@Table(name = "mydata")
@NamedQuery(
        name = "findWithName",
        query = "from MyData where name like :fname"
)
public class MyData {}
```

NamedQuery