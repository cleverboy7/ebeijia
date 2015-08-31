<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ebeijia.util.Constant"%>
<ipy-template ipy-id="dynamic-table-paginate">
	<div class="paginate clear-fix">
		<div class="info">
			共 <span class="total-rows"></span> 条记录，
			显示第 <span class="begin-row"></span> 条到第 <span class="end-row"></span> 条
		</div>
		<div class="paginate-nav">
			<span class="paginate-button first">第一页</span>
			<span class="paginate-button previous">上一页</span>
			<span class="page-indexes"></span>
			<span class="paginate-button next">下一页</span>
			<span class="paginate-button last">最后一页</span>
		</div>
	</div>
</ipy-template>