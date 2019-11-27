<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" type="text/css" href="./css/radish.css">
<link rel="stylesheet" type="text/css" href="./css/table.css">
<title>決済確認画面</title>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<script src="./js/settlementConfirm.js"></script>
	<div id="contents">
		<h1>決済確認画面</h1>
		<div>
			<s:if test="destinationInfoDTOList!=null && destinationInfoDTOList.size > 0">

				<div class="info">
				宛先情報を選択してください。
				</div>
				<s:form id="settlementConfirmForm">
				<table class="buyTable">
				<tr>
					<th><s:label value="#"/></th>
					<th><s:label value="姓"/></th>
					<th><s:label value="名"/></th>
					<th><s:label value="ふりがな"/></th>
					<th><s:label value="住所"/></th>
					<th><s:label value="電話番号"/></th>
					<th><s:label value="メールアドレス"/></th>
				</tr>
				<s:iterator value="destinationInfoDTOList" status="st">
					<tr>
						<td>
						<s:if test="#st.index==0">
						<input type="radio" name="id" checked="checked" value="<s:property value='id'/>"/>
						</s:if>
						<s:else>
						<input type="radio" name="id" value="<s:property value='id'/>"/>
						</s:else>
						</td>
						<td>
							<s:property value="familyName"/>
						</td>
						<td>
							<s:property value="firstName"/>
						</td>
						<td>
							<s:property value="familyNameKana"/><span>　</span><s:property value="firstNameKana"/>
						</td>
						<td>
							<s:property value="userAddress"/>
						</td>
						<td>
							<s:property value="telNumber"/>
						</td>
						<td>
							<s:property value="email"/>
						</td>
					</tr>
				</s:iterator>
				</table>
					<div class="submitBtnBox">
					<s:submit  value="決済" class="submitBtn" onClick="goSettlementCompleteAction()"/>
					</div>
					<div class="submitBtnBox">
					<s:submit value="削除" class="submitBtn" onClick="goDeleteDestinationAction()"/>
					</div>
			</s:form>
				</s:if>
			<s:else>
				<div class="info">
				宛先情報がありません。
				</div>
			</s:else>
			<s:form action="CreateDestinationAction">
				<div class="submitBtnBox">
				<s:submit value="新規宛先登録" class="submitBtn"/>
				</div>
			</s:form>
		</div>
	</div>
</body>
</html>