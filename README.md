# SSOTEST Project

โปรเจกต์นี้เป็นตัวอย่างการใช้ Spring Boot ร่วมกับ PostgreSQL เพื่อบันทึกข้อมูลลงฐานข้อมูลโดยใช้ JPA (Java Persistence API)

## การติดตั้งและตั้งค่า

### 1. การติดตั้ง PostgreSQL

1. ดาวน์โหลดและติดตั้ง PostgreSQL จาก [เว็บไซต์ PostgreSQL](https://www.postgresql.org/download/).
2. ตั้งค่าฐานข้อมูลชื่อ `ssotest` และผู้ใช้ `ssodev` ใน PostgreSQL (หากยังไม่ได้ทำการตั้งค่า):
    ```sql
    CREATE DATABASE ssotest;
    CREATE USER ssodev WITH ENCRYPTED PASSWORD 'sso2022ok';
    GRANT ALL PRIVILEGES ON DATABASE ssotest TO ssodev;
    ```

### 2. การตั้งค่าโปรเจกต์

1. ติดตั้ง Java Development Kit (JDK) เวอร์ชัน 17 หรือสูงกว่า
2. สร้างไฟล์ `application.properties` สำหรับการเชื่อมต่อฐานข้อมูลในโฟลเดอร์ `src/main/resources`:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/ssotest
    spring.datasource.username=ssodev
    spring.datasource.password=sso2022ok
    spring.datasource.driver-class-name=org.postgresql.Driver
    spring.datasource.platform=postgresql

    spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```

3. ตรวจสอบให้แน่ใจว่าโปรเจกต์ใช้ Spring Boot เวอร์ชันล่าสุดในไฟล์ `pom.xml`:
    ```xml
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.6.0</version>
        </dependency>
    </dependencies>
    ```

### 3. การรันโปรเจกต์

1. ใช้คำสั่ง Maven หรือ Gradle ในการรันโปรเจกต์:
    ```bash
    mvn spring-boot:run
    ```

2. หลังจากรันโปรเจกต์แล้ว คุณสามารถทดสอบ API ที่สามารถใช้ในการส่งข้อมูลได้ที่ `http://localhost:8080/apitest/gentoken` โดยส่งข้อมูลเป็น JSON ที่มีโครงสร้างตาม `TestEntity`.

### 4. การทดสอบ API

- API ที่ให้บริการคือ `POST /apitest/gentoken`
- รูปแบบ JSON ที่ส่งไป:
    ```json
    {
      "ssotype": "example",
      "systemid": "example-id",
      "systemname": "example-name",
      "systemtransactions": "example-transactions",
      "systemprivileges": "example-privileges",
      "systemusergroup": "example-group",
      "systemlocationgroup": "example-location-group",
      "userid": "user123",
      "userfullname": "John Doe",
      "userrdofficecode": "office-001",
      "userofficecode": "office-002",
      "clientlocation": "location-001",
      "locationmachinenumber": "machine-001",
      "tokenid": "token-001"
    }
    ```

### 5. การบันทึกข้อมูล

เมื่อข้อมูลถูกส่งไปยัง API ข้างต้น ระบบจะบันทึกข้อมูลลงในตาราง `sso_user_test` ของฐานข้อมูล PostgreSQL และส่งกลับข้อความยืนยันการทำงานในรูปแบบ JSON

### 6. โครงสร้างโค้ด

- **TestController**: ควบคุมการรับและส่งข้อมูลจาก API
- **TestEntity**: ตัวแทนของข้อมูลที่ต้องการบันทึกลงในฐานข้อมูล
- **TestService**: ใช้ในการเชื่อมต่อกับ `TestRepository` เพื่อบันทึกข้อมูล
- **TestRepository**: Repository ที่ใช้ในการจัดการข้อมูลกับฐานข้อมูล
- **Response**: คลาสที่ใช้ส่งข้อมูลกลับในรูปแบบของ API Response

## การพัฒนาเพิ่มเติม

- คุณสามารถพัฒนาเพิ่มเติมเกี่ยวกับฟีเจอร์ต่างๆ ของโปรเจกต์นี้ เช่น การตรวจสอบข้อมูลก่อนบันทึก การเพิ่ม API สำหรับดึงข้อมูล หรือการจัดการข้อผิดพลาดที่ซับซ้อนมากขึ้น

## สรุป

โปรเจกต์นี้เป็นตัวอย่างการใช้งาน Spring Boot และ PostgreSQL สำหรับการจัดการข้อมูลแบบง่ายๆ โดยใช้ JPA และ Hibernate ในการจัดการกับฐานข้อมูลแบบอัตโนมัติ
