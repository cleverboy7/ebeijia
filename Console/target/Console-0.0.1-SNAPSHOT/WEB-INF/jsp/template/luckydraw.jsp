<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ebeijia.util.Constant"%>
<ipy-template ipy-id="luckydraw-form">
	<div class="luckydraw-input-group">
		<div class="input-wrapper required">
			<label>奖品名称：</label>
			<div class="input-item input-medium">
				<input type="text" name="prName">
				<div class="required-mark"></div>
			</div>
		</div>
		<div class="input-wrapper">
			<label>奖品描述：</label>
			<div class="input-item input-medium">
				<input type="text" name="prize">
				<div class="required-mark"></div>
			</div>
		</div>
		<div class="input-wrapper required">
			<label>中奖概率：</label>
			<div class="input-item input-medium">
				<input type="text" name="probability">
				<div class="required-mark"></div>
			</div>
		</div>
		<div class="input-wrapper">
			<label>最大中奖数：</label>
			<div class="input-item input-medium">
				<input type="text" name="winNum">
				<div class="required-mark"></div>
			</div>
		</div>
		<button class="btn btn-primary">
			<span class="fa fa-minus-circle"></span>
			删除
		</button>
	</div>
</ipy-template>

<ipy-template ipy-id="bigWheel">
	<div class="bigWheel">
		<div class="bigWheel-body"></div>
		<div class="bigWheel-needle"></div>
		<div class="bigWheel-center">
			<div class="bigWheel-center-inner"></div>
		</div>
	</div>
</ipy-template>

<ipy-template ipy-id="bigWheel-sector">
	<div class="bigWheel-sector">
		<div class="bigWheel-hold">
			<div class="bigWheel-pie"></div>
		</div>
	</div>
</ipy-template>