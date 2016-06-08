<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<%--sql:query var="categories" dataSource="jdbc/accessapparel">
    SELECT * FROM category
</sql:query>--%>

            <div id="indexLeftColumn">
                <div id="welcomeText">
                    <p style="font-size: larger">Welcome to the official Access Apparel website!</p>

                    <p>Access Apparel is an online store where you can get your favourite apparel from you favourite brands.</p>
                </div>
            </div>

            <div id="indexRightColumn">
                <c:forEach var="category" items="${categories}">
                    <div class="categoryBox">
                        <a href="<c:url value='category?${category.id}'/>">
                            <span class="categoryLabel"></span>
                            <span class="categoryLabelText">${category.name}</span>

                            <img src="${initParam.categoryImagePath}${category.name}.jpg"
                                 alt="${category.name}" class="categoryImage">
                        </a>
                    </div>
                </c:forEach>
            </div>