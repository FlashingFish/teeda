<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<html>
<head>
<title>dataTable3.jsp</title>
</head>
<body>
<f:view>
	<h:dataTable value="#{dataTableBean.items}" var="item" border="1">
		<f:facet name="header">
			<h:outputText value="products"/>
		</f:facet>
		<f:facet name="footer">
			<h:panelGroup>
				<h:outputText value="#{dataTableBean.itemSize}"/>
				<h:outputText value=" items."/>
			</h:panelGroup>
		</f:facet>
		<h:column>
			<f:facet name="header">
				<h:outputText value="name"/>
			</f:facet>
			<h:outputText value="#{item.name}" />
		</h:column>
		<h:column>
			<f:facet name="header">
				<h:outputText value="price"/>
			</f:facet>
			<h:outputText value="#{item.price}" />
		</h:column>
	</h:dataTable>
</f:view>
</body>
</html>
