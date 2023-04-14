
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<head>
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>

<h2>
	<acme:message code="lecturer.lecturer-dashboard.form.title"/>
</h2>

<table style="margin: 0 auto;">
	<thead>
		<tr>
			<th scope="col"></th>
			<th scope="col"><acme:message code="lecturer.lecturer-dashboard.total"/></th>
			<th scope="col"><acme:message code="lecturer.lecturer-dashboard.average"/></th>
			<th scope="col"><acme:message code="lecturer.lecturer-dashboard.desv"/></th>
			<th scope="col"><acme:message code="lecturer.lecturer-dashboard.min"/></th>
			<th scope="col"><acme:message code="lecturer.lecturer-dashboard.max"/></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<th scope="row"><acme:message code="lecturer.lecturer-dashboard.form.theory"/></th>
			<td>${totalNumberOfTheoryLectures}</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<th scope="row"><acme:message code="lecturer.lecturer-dashboard.form.handOn"/></th>
			<td>${totalNumberOfHandsOnLectures}</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<th scope="row"><acme:message code="lecturer.lecturer-dashboard.form.averageLecture"/></th>
			<td></td>
			<td>${averageLearningTimeOfLectures}</td>
			<td>${deviationLearningTimeOfLectures}</td>
			<td>${minimumLearningTimeOfLectures}</td>
			<td>${maximumLearningTimeOfLectures}</td>
		</tr>
		<tr>
			<th scope="row"><acme:message code="lecturer.lecturer-dashboard.form.averageCourse"/></th>
			<td></td>
			<td>${averageLearningTimeOfCourses}</td>
			<td>${deviationLearningTimeOfCourses}</td>
			<td>${minimumLearningTimeOfCourses}</td>
			<td>${maximumLearningTimeOfCourses}</td>
		</tr>
	</tbody>
</table>

<div>
	<canvas style="max-width: 500px; max-height: 500px; margin: 0 auto; display: block; padding-top: 2%;" id="graf"></canvas>
</div>

  <script>
    var contexto = document.getElementById('graf').getContext('2d');
 	
    var datos =  [
		<jstl:out value="${averageLearningTimeOfCourses}"/>, 
		<jstl:out value="${deviationLearningTimeOfCourses}"/>, 
		<jstl:out value="${minimumLearningTimeOfCourses}"/>,
		<jstl:out value="${maximumLearningTimeOfCourses}"/>
	]
 	var etiquetas = [
		"AVERAGE", "DEVIATION", "MINIMUM", "MAXIMUM"
		]
    var colores =  [
        'rgba(255, 99, 132, 0.5)',
        'rgba(54, 162, 235, 0.5)',
        'rgba(255, 206, 86, 0.5)',
        'rgba(75, 192, 192, 0.5)'
      ]
 	
    var configuracion = {
      type: 'pie',
      data: {
        datasets: [{
          data: datos,
          backgroundColor: colores,
        }],
        labels: etiquetas
      },
      options: {
        responsive: true,
        legend: {
          display: true,
          position: 'bottom',
        }
      }
    };

    new Chart(contexto, configuracion);
  </script>
  
  <style>
  table {
  border-collapse: collapse;
}

td,
th {
  padding: 1rem;
  text-align: center;
}
  </style>
<acme:return/>

