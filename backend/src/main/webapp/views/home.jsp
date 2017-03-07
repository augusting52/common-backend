<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" class="loading">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta name="viewport" content="width=device-width, initial-scale=0.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="keywords" content="教授说 下午茶 教授" >
<meta name="description" content="教授下午茶-网站主页">
<meta name="author" content="李炳" >
<link rel="stylesheet" type="text/css" href="/static/css/styles.css" >
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<link rel="apple-touch-icon-precomposed" href="http://www.17sucai.com/static/images/favicon.ico">
<script>
var logined = 0
</script>
<title>Common-Backend</title>
</head>

<body>
<script>
var now_page = 1,
search_value = '';
</script>

		<div id="menu">
		<div class="search_wrap">
			<form action="" method="get">
				<input type="text" name="search" class="search_input" placeholder="查找" />
				<i class="reset_input"><i></i></i>
			</form>
		</div>
		<ul>
			<li class="nav_index menu_cur"><a href=""><i></i><span>F-Video</span><b></b><div class="clear"></div></a></li>
			<li class="nav_site"><a href=""><i></i><span>下午茶</span><b></b><div class="clear"></div></a></li>
			<li class="nav_help"><a href=""><i></i><span>预定</span><b></b><div class="clear"></div></a></li>
			<li class="nav_about"><a href=""><i></i><span>福利</span><b></b><div class="clear"></div></a></li>
			<li class="nav_about"><a href=" "><i></i><span>L-Video</span><b></b><div class="clear"></div></a></li>
		</ul>
	</div>
		<div id="user">
					<div class="account">
				<div class="login_b_t">
					<div class="pd10">
						<div class="fl">还没有账号<a id="reg_now" href="" onclick="return false;">立即注册</a></div><div class="fr"><a href="#">忘记密码?</a></div><div class="clear"></div>
					</div>
				</div>
			</div>
			<div class="pd10">
				<form method="post" action="">
					<div class="login_b_i">
						<div class="login_input">
							<div class="login_user"><input type="text" name="email" placeholder="邮箱/帐号" /><i></i></div>
							<div class="login_password"><input type="password" name="password" placeholder="密码" /><i></i></div>
						</div>
					</div>
					<a class="login_submit">登录</a>
				</form>
				<div class="login_quick">
					<p>用第三方帐号直接登录</p>
					<a href="http://www.17sucai.com/oauth/weibo/login" id="weibo_app"><span><i></i>微博帐号登录</span></a>
					<a href="http://www.17sucai.com/oauth/qq/login" id="qq_connect"><span><i></i>QQ&nbsp;&nbsp;帐号登录</span></a>
				</div>
			</div>
			</div>	
	<div id="header">
		<div class="wrap">
			<i class="menu_open"></i>
			<div class="logo"><a href="http://professortea.sinaapp.com/" title="教授下午茶"><img src="/static/css/logo.png" /></a></div>
			<i class="search_open"></i>
		</div>
		<div class="logo_msk"></div>
	</div>
	<div id="container">
		<div id="sort">
			<table width="100%" border="0" cellspacing="0">
				<tr>
					<td class="sort_left">
						<div class="sort_cate">
							<div class="sort_b"><a href="#" onclick="return false;"><div class="sort_b_inner"><i class="cate_default"></i><span>全部分类</span><div class="clear"></div></div></a></div>
						</div>
					</td>
					<td>
						<div class="sort_sort">
							<div class="sort_b"><a href="#" onclick="return false;"><div class="sort_b_inner"><i class="cate_sort"></i><span>最新发布</span><div class="clear"></div></div></a></div>
						</div>
					</td>
					<td class="sort_right">
						<div class="sort_tag">
							<div class="sort_b"><a href="#" onclick="return false;"><div class="sort_b_inner"><i class="cate_tag"></i><span>版权</span><div class="clear"></div></div></a></div>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div id="content">	
			<div id="list">
				<ul>
										<li>
						<div class="wrap">
							<a class="alist" vhref="detail">
								<div class="list_litpic fl"><img src="/static/images/1421841277109.jpg" /></div>
								<div class="list_info">
									<h4>红豆薏米饮品</h4>
									<h5>by<span>经验分享</span><em>(其他)</em></h5>
									<div class="list_info_i">
										<dl class="list_info_views">
											<dt></dt>
											<dd>6907</dd>
											<div class="clear"></div>
										</dl>
										<dl class="list_info_comment">
											<dt></dt>
											<dd>5</dd>
											<div class="clear"></div>
										</dl>
										<dl class="list_info_like">
											<dt></dt>
											<dd>8</dd>
											<div class="clear"></div>
										</dl>
										<div class="clear"></div>
									</div>
								</div>
								<div class="clear"></div>
							</a>
						</div>
					</li>
										<li>
						<div class="wrap">
							<a class="alist" vhref="detail">
								<div class="list_litpic fl"><img src="/static/images/1421838333851.jpg" /></div>
								<div class="list_info">
									<h4>动画写实场景</h4>
									<h5>by<span>木白</span><em>(其他)</em></h5>
									<div class="list_info_i">
										<dl class="list_info_views">
											<dt></dt>
											<dd>186</dd>
											<div class="clear"></div>
										</dl>
										<dl class="list_info_comment">
											<dt></dt>
											<dd>0</dd>
											<div class="clear"></div>
										</dl>
										<dl class="list_info_like">
											<dt></dt>
											<dd>1</dd>
											<div class="clear"></div>
										</dl>
										<div class="clear"></div>
									</div>
								</div>
								<div class="clear"></div>
							</a>
						</div>
					</li>
										<li>
						<div class="wrap">
							<a class="alist" vhref="detail">
								<div class="list_litpic fl"><img src="/static/images/1421805669559.jpg" /></div>
								<div class="list_info">
									<h4>太白案例更新+ui页面</h4>
									<h5>by<span>u21</span><em>(企业集团)</em></h5>
									<div class="list_info_i">
										<dl class="list_info_views">
											<dt></dt>
											<dd>423</dd>
											<div class="clear"></div>
										</dl>
										<dl class="list_info_comment">
											<dt></dt>
											<dd>0</dd>
											<div class="clear"></div>
										</dl>
										<dl class="list_info_like">
											<dt></dt>
											<dd>1</dd>
											<div class="clear"></div>
										</dl>
										<div class="clear"></div>
									</div>
								</div>
								<div class="clear"></div>
							</a>
						</div>
					</li>
										<li>
						<div class="wrap">
							<a class="alist" vhref="detail">
								<div class="list_litpic fl"><img src="/static/images/1421805477901.jpg" /></div>
								<div class="list_info">
									<h4>白 .</h4>
									<h5>by<span>郗鉴</span><em>(图标Icon)</em></h5>
									<div class="list_info_i">
										<dl class="list_info_views">
											<dt></dt>
											<dd>876</dd>
											<div class="clear"></div>
										</dl>
										<dl class="list_info_comment">
											<dt></dt>
											<dd>2</dd>
											<div class="clear"></div>
										</dl>
										<dl class="list_info_like">
											<dt></dt>
											<dd>10</dd>
											<div class="clear"></div>
										</dl>
										<div class="clear"></div>
									</div>
								</div>
								<div class="clear"></div>
							</a>
						</div>
					</li>
										<li>
						<div class="wrap">
							<a class="alist" vhref="detail">
								<div class="list_litpic fl"><img src="/static/images/1421763729558.jpg" /></div>
								<div class="list_info">
									<h4>MEIOS(决赛补齐界面)</h4>
									<h5>by<span>yu0910</span><em>(图标Icon)</em></h5>
									<div class="list_info_i">
										<dl class="list_info_views">
											<dt></dt>
											<dd>1238</dd>
											<div class="clear"></div>
										</dl>
										<dl class="list_info_comment">
											<dt></dt>
											<dd>1</dd>
											<div class="clear"></div>
										</dl>
										<dl class="list_info_like">
											<dt></dt>
											<dd>8</dd>
											<div class="clear"></div>
										</dl>
										<div class="clear"></div>
									</div>
								</div>
								<div class="clear"></div>
							</a>
						</div>
					</li>
										<li>
						<div class="wrap">
							<a class="alist" vhref="detail">
								<div class="list_litpic fl"><img src="/static/images/1421742102374.jpg" /></div>
								<div class="list_info">
									<h4>5年前的游戏icon及地图绘制</h4>
									<h5>by<span>又见章鱼哥</span><em>(图标Icon)</em></h5>
									<div class="list_info_i">
										<dl class="list_info_views">
											<dt></dt>
											<dd>1228</dd>
											<div class="clear"></div>
										</dl>
										<dl class="list_info_comment">
											<dt></dt>
											<dd>0</dd>
											<div class="clear"></div>
										</dl>
										<dl class="list_info_like">
											<dt></dt>
											<dd>1</dd>
											<div class="clear"></div>
										</dl>
										<div class="clear"></div>
									</div>
								</div>
								<div class="clear"></div>
							</a>
						</div>
					</li>
										<li>
						<div class="wrap">
							<a class="alist" vhref="detail">
								<div class="list_litpic fl"><img src="/static/images/142174136543.jpg" /></div>
								<div class="list_info">
									<h4>&lt;hello logo&gt;特别篇——动物畅想曲（1）</h4>
									<h5>by<span>君小阳</span><em>(图形设计)</em></h5>
									<div class="list_info_i">
										<dl class="list_info_views">
											<dt></dt>
											<dd>1264</dd>
											<div class="clear"></div>
										</dl>
										<dl class="list_info_comment">
											<dt></dt>
											<dd>4</dd>
											<div class="clear"></div>
										</dl>
										<dl class="list_info_like">
											<dt></dt>
											<dd>3</dd>
											<div class="clear"></div>
										</dl>
										<div class="clear"></div>
									</div>
								</div>
								<div class="clear"></div>
							</a>
						</div>
					</li>
										<li>
						<div class="wrap">
							<a class="alist" vhref="detail">
								<div class="list_litpic fl"><img src="/static/images/1421734678611.jpg" /></div>
								<div class="list_info">
									<h4>离职后做的一个外包</h4>
									<h5>by<span>烽烟</span><em>(企业集团)</em></h5>
									<div class="list_info_i">
										<dl class="list_info_views">
											<dt></dt>
											<dd>1441</dd>
											<div class="clear"></div>
										</dl>
										<dl class="list_info_comment">
											<dt></dt>
											<dd>1</dd>
											<div class="clear"></div>
										</dl>
										<dl class="list_info_like">
											<dt></dt>
											<dd>7</dd>
											<div class="clear"></div>
										</dl>
										<div class="clear"></div>
									</div>
								</div>
								<div class="clear"></div>
							</a>
						</div>
					</li>
										<li>
						<div class="wrap">
							<a class="alist" vhref="detail">
								<div class="list_litpic fl"><img src="/static/images/1421726012956.jpg" /></div>
								<div class="list_info">
									<h4>Bodhi</h4>
									<h5>by<span>酷站推荐</span><em>(企业集团)</em></h5>
									<div class="list_info_i">
										<dl class="list_info_views">
											<dt></dt>
											<dd>1987</dd>
											<div class="clear"></div>
										</dl>
										<dl class="list_info_comment">
											<dt></dt>
											<dd>0</dd>
											<div class="clear"></div>
										</dl>
										<dl class="list_info_like">
											<dt></dt>
											<dd>3</dd>
											<div class="clear"></div>
										</dl>
										<div class="clear"></div>
									</div>
								</div>
								<div class="clear"></div>
							</a>
						</div>
					</li>
										<li>
						<div class="wrap">
							<a class="alist" vhref="detail">
								<div class="list_litpic fl"><img src="/static/images/1420788431778.jpg" /></div>
								<div class="list_info">
									<h4>复古小清新rosevovo</h4>
									<h5>by<span>妞范儿</span><em>(门户电商)</em></h5>
									<div class="list_info_i">
										<dl class="list_info_views">
											<dt></dt>
											<dd>3186</dd>
											<div class="clear"></div>
										</dl>
										<dl class="list_info_comment">
											<dt></dt>
											<dd>19</dd>
											<div class="clear"></div>
										</dl>
										<dl class="list_info_like">
											<dt></dt>
											<dd>38</dd>
											<div class="clear"></div>
										</dl>
										<div class="clear"></div>
									</div>
								</div>
								<div class="clear"></div>
							</a>
						</div>
					</li>
										<li>
						<div class="wrap">
							<a class="alist" vhref="detail">
								<div class="list_litpic fl"><img src="/static/images/142172644254.jpg" /></div>
								<div class="list_info">
									<h4>另类设计后期欣赏</h4>
									<h5>by<span>Photography</span><em>(摄影后期)</em></h5>
									<div class="list_info_i">
										<dl class="list_info_views">
											<dt></dt>
											<dd>4629</dd>
											<div class="clear"></div>
										</dl>
										<dl class="list_info_comment">
											<dt></dt>
											<dd>33</dd>
											<div class="clear"></div>
										</dl>
										<dl class="list_info_like">
											<dt></dt>
											<dd>46</dd>
											<div class="clear"></div>
										</dl>
										<div class="clear"></div>
									</div>
								</div>
								<div class="clear"></div>
							</a>
						</div>
					</li>
					<li>
						<div class="wrap">
							<a class="alist" vhref="detail">
								<div class="list_litpic fl"><img src="/static/images/1421207067537.jpg" /></div>
								<div class="list_info">
									<h4>2010-2014平面类作品合集</h4>
									<h5>by<span>记忆拼图_memory</span><em>(VI/CI)</em></h5>
									<div class="list_info_i">
										<dl class="list_info_views">
											<dt></dt>
											<dd>4775</dd>
											<div class="clear"></div>
										</dl>
										<dl class="list_info_comment">
											<dt></dt>
											<dd>2</dd>
											<div class="clear"></div>
										</dl>
										<dl class="list_info_like">
											<dt></dt>
											<dd>13</dd>
											<div class="clear"></div>
										</dl>
										<div class="clear"></div>
									</div>
								</div>
								<div class="clear"></div>
							</a>
						</div>
					</li>
									</ul>
				<div class="list_loading"><i></i><span>努力加载中...</span></div>
			</div>
		</div>
		
		<div class="push_msk"></div>
	</div>

	<div id="sort_content">
		<div class="asort">
			<div class="hd">
				<div class="wrap">
					<div class="fl"><span>选择分类</span><div class="clear"></div></div>
					<div class="fr"></div>
					<div class="clear"></div>
				</div>
			</div>
			<div class="ct">
				<div class="wrap">
					<ul class="choose_cate">
						<li c_data="0" style="font-weight:700;"><i style="background:none;width:0;margin-right:0;" class="s"></i><span>全部分类</span><i class="e"></i></li>
													<li style="font-weight:700;" c_data="1" style="background:#f7f7f7;"><i style="margin-right:0;background:none;width:0;" class="s"></i><span>F-Videos:短视频</span><i class="e"></i></li>
															<li c_data="2"><i style="background:none;width:10px;margin-right:0;" class="s"></i><span>饮品类</span><i class="e"></i></li>
															<li c_data="25"><i style="background:none;width:10px;margin-right:0;" class="s"></i><span>点心类</span><i class="e"></i></li>
															<li c_data="11"><i style="background:none;width:10px;margin-right:0;" class="s"></i><span>其他</span><i class="e"></i></li>
																		</ul>
					<div class="clear"></div>
				</div>
			</div>
		</div>
				
		<div class="asort">
			<div class="hd">
				<div class="wrap">
					<div class="fl"><span>选择排序</span><div class="clear"></div></div>
					<div class="fr"></div>
					<div class="clear"></div>
				</div>
			</div>
			<div class="ct">
				<div class="wrap">
					<ul class="choose_sort">
													<li class="a_selected" s_data="rec"><i class="s"></i><span>最新推荐</span><i class="e"></i></li>
													<li s_data="like"><i class="s"></i><span>最多人气</span><i class="e"></i></li>
													<li s_data="view"><i class="s"></i><span>最多浏览</span><i class="e"></i></li>
													<li s_data="comment"><i class="s"></i><span>最多销量</span><i class="e"></i></li>
													<li s_data="laster"><i class="s"></i><span>最新发布</span><i class="e"></i></li>
											</ul>
					<div class="clear"></div>
				</div>
			</div>
		</div>

		<div class="asort">
			<div class="hd">
				<div class="wrap">
					<div class="fl"><i></i><span>版权</span><div class="clear"></div></div>
					<div class="fr"></div>
					<div class="clear"></div>
				</div>
			</div>
			<div class="ct">
				<div class="wrap">
					<!--<h4 class="cate_trade"><i></i><span>行业</span></h4>-->
					<ul>
												<li t_data=""><i></i><span>所有者</span><i class="e"></i></li>
												<li t_data="1"><i></i><span>原创</span><i class="e"></i></li>
												<li t_data="2"><i></i><span>转载</span><i class="e"></i></li>
											</ul>
					<div class="clear"></div>
				</div>
			</div>
		</div>
		
	</div>
	
	<div id="newwrap_t" class="newwrap">
		<div class="newwrap_msk"></div>
		<iframe id="newwrap" frameborder="0" width="100%" height="100%"></iframe>
	</div>
	<div id="reg_index">
		<div class="reg_bar">
			<div class="wrap">
				<span class="fl"><i></i>注册帐号</span>
				<i class="reg_bar_close fr"></i>
				<div class="clear"></div>
			</div>
		</div>
		<div class="wrap reg_ct">
			<p>您可以选择以下第三方帐号直接登录uehtml<br />一分钟完成注册</p>
			<a href="http://www.17sucai.com/oauth/weibo/login" id="weibo_app"><span><i></i>微博帐号登录</span></a>
			<a href="http://www.17sucai.com/oauth/qq/login" id="qq_connect"><span><i></i>QQ&nbsp;&nbsp;帐号登录</span></a>
		</div>
	</div>
	<div class="loading_dark"></div>
	<div id="loading_mask">
		<div class="loading_mask">
			<ul class="anm">
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
			</ul>
		</div>
	</div>
	<script language="javascript" src="/static/js/zepto.min.js"></script>
	<script language="javascript" src="/static/js/fx.js"></script>
	<script language="javascript" src="/static/js/script.js"></script>
	
</body>
</html>