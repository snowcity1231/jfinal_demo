${title}</br>
共${blogList?size}篇</br>
<#list blogList as blog>
	<a href="#">${blog_index+1}.${blog.title}</a><br>
</#list>
