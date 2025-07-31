
# Time tracking - Gestión de empleados y control de horario

Aplicación web desarrollada con **Spring Boot** y **Thymeleaf** para la gestión de empleados, departamentos, roles y control horario.  
Incluye dos vistas principales:
- 👤 **Vista de usuario** (fichaje y gestión de su propio horario)
- 🛠️ **Vista de administrador** (gestión de empleados, departamentos, roles y jornadas laborales)

---

## 🚀 Tecnologías utilizadas

- **Java 17+**
- **Spring Boot 3**
    - Spring MVC
    - Spring Data JPA
    - Spring Security
    - Validación con Hibernate Validator
- **Thymeleaf** + **Thymeleaf Extras Spring Security**
- **Bootstrap 5.3**
- **jQuery / AJAX**
- **MySQL**
- **Maven**

---

## 🔐 Roles de usuario

- **Admin (`ROLE_ADMIN`)**:
    - Acceso al panel `/admin/index`
    - Gestiona empleados, departamentos, roles y tipos de jornada
    - Puede ver la vista de usuario y el botón para volver a administración
- **Usuario (`ROLE_USER`)**:
    - Acceso a `/user/index`
    - Puede fichar entrada/salida y consultar su horario

---

## 📸 Funcionalidades principales

✅ **CRUD de empleados** con modal dinámico y validación  
✅ **Gestión de departamentos y roles**  
✅ **Asignación de tipo de jornada laboral (WorkingType)**  
✅ **Control horario**: fichajes diarios con entrada, salida y pausas  
✅ **Seguridad con Spring Security**:
   - Login
   - Roles y permisos
   - Ocultación de botones según el rol (`sec:authorize`)  
✅ **Validaciones en servidor y cliente**  
✅ **Diseño responsive con Bootstrap 5**

---


