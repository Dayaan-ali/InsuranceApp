<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr"
    crossorigin="anonymous">

<title>Insurance Application</title>

<style>
body {
    font-family: Arial, sans-serif;
    padding: 30px;
    text-align: center;
    margin: 0;
    background-image: url('/images/bg.jpg');
    background-size: cover;
    background-repeat: no-repeat;
    background-position: center;
    background-attachment: fixed;
    color: #000;
}

.container {
    width: 100%;
    padding: 30px;
}

h1 {
    margin-bottom: 50px;
}

.row {
    margin-bottom: 30px;
}

label {
    font-weight: bold;
    margin-bottom: 8px;
    display: block;
}

.form-control {
	margin-bottom: 15px;
	border: 1px solid black;
	box-shadow: none;
	background-color: transparent; /* 👈 Add this line */
	color: black; /* Text visible on background */
}

.btn {
    width: 120px;
}

.export-title {
    margin-top: 2rem;
    margin-bottom: 20px;
    font-size: 18px;
    font-weight: bold;
}

.export-links a {
    margin: 0 20px;
    text-decoration: none;
    font-weight: bold;
}

.form-card {
	border-bottom: 1px solid black;
	margin-bottom: 0px;
}

.footer-container {
	border-top: 1px solid black;
	margin-top: 96px;
	padding-top: 0px;
}

table {
    border-collapse: collapse;
    width: 100%;
    background-color: rgba(255, 255, 255, 0.9);
    border-radius: 8px;
}

th, td {
    padding: 8px;
    text-align: left;
    border-bottom: 1px solid #ddd;
}

tr:hover {
    background-color: #6c757d66;
    cursor: pointer;
}
</style>
</head>
<body>

    <div class="container">
        <div class='form-card'>
            <h1>Insurance Application</h1>

            <form:form action="search" modelAttribute="search" method="POST">

                <div class="row justify-content-center mb-4">
                    <div class="col-md-3">
                        <label>Plan Name</label>
                        <form:select path="planName" cssClass="form-control">
                            <form:option value="">--select--</form:option>
                            <form:options items="${names}" />
                        </form:select>
                    </div>
                    <div class="col-md-3">
                        <label>Plan Status</label>
                        <form:select path="planStatus" cssClass="form-control">
                            <form:option value="">--select--</form:option>
                            <form:options items="${status}" />
                        </form:select>
                    </div>
                    <div class="col-md-3">
                        <label>Gender</label>
                        <form:select path="gender" cssClass="form-control">
                            <form:option value="">--select--</form:option>
                            <form:option value="Male">Male</form:option>
                            <form:option value="Female">Female</form:option>
                        </form:select>
                    </div>
                </div>

                <div class="row justify-content-center mb-4">
                    <div class="col-md-3">
                        <label>Start Date</label>
                        <form:input path="startDate" type="date" cssClass="form-control" />
                    </div>
                    <div class="col-md-3">
                        <label>End Date</label>
                        <form:input path="endDate" type="date" cssClass="form-control" />
                    </div>
                </div>

                <div class="row justify-content-center mb-5">
                    <div class="col-md-3 d-flex justify-content-center gap-4">
                        <input class="btn btn-primary" type="submit" value="Search" />
                        <a href="/" class="btn btn-secondary">Reset</a>
                    </div>
                </div>

            </form:form>
        </div>

        <c:if test="${not empty plans}">
            <table>
                <thead>
                    <tr>
                        <th>S.No</th>
                        <th>Citizen Name</th>
                        <th>Gender</th>
                        <th>Plan Name</th>
                        <th>Plan Status</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Benefit Amount</th>
                    </tr>
                </thead>
                <tbody class="table-data">
                    <c:forEach items="${plans}" var="plan" varStatus="index">
                        <tr>
                            <td>${index.count}</td>
                            <td>${plan.citizenName}</td>
                            <td>${plan.gender}</td>
                            <td>${plan.planName}</td>
                            <td>${plan.planStatus}</td>
                            <td>${plan.planStartDate}</td>
                            <td>${plan.planEndDate}</td>
                            <td>${plan.benefitAmount}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${isSearch and empty plans}">
            <p style="color: red;">No records found.</p>
        </c:if>

        <div class='footer-container'>
            <div class="export-title">Export:</div>
            <div class="row justify-content-center export-links">
                <div class="col-md-auto">
                    <a href="excel">Excel</a>
                </div>
                <div class="col-md-auto">
                    <a href="pdf">Pdf</a>
                </div>
            </div>
        </div>
    </div>

    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q"
        crossorigin="anonymous"></script>
</body>
</html>