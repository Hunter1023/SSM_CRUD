<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--显示分页信息--%>
<div class="row">
    <%--分页文字信息--%>
    <div class="col-md-6">
        当前${pageInfo.pageNum}页，总${pageInfo.pages}页,总${pageInfo.total}条记录
    </div>
    <%--分页条信息--%>
    <div class="col-md-6">
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <li><a href="?pageNum=1">首页</a></li>
                <c:if test="${pageInfo.hasPreviousPage}">
                    <li>
                        <a href="?pageNum=${pageInfo.pageNum-1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:forEach items="${pageInfo.navigatepageNums}" var="pageNum">
                    <c:if test="${pageNum == pageInfo.pageNum}">
                        <li class="active"><a href="#">${pageNum}</a></li>
                    </c:if>
                    <c:if test="${pageNum != pageInfo.pageNum}">
                        <li><a href="?pageNum=${pageNum}">${pageNum}</a></li>
                    </c:if>
                </c:forEach>

                <c:if test="${pageInfo.hasNextPage}">
                    <li>
                        <a href="?pageNum=${pageInfo.pageNum+1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
                <li><a href="?pageNum=${pageInfo.pages}">尾页</a></li>
            </ul>
        </nav>
    </div>
</div>
