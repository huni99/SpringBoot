const pn =document.getElementsByClassName("pn");
const pn2 = document.querySelectorAll(".pn");
const btn = document.getElementsByClassName("btn");
const searchForm = document.getElementById("searchForm");
const pageNum = document.querySelector("#pageNum");


pn2.forEach(function(item){
	item.addEventListener("click",function(){
		let n = this.getAttribute("data-pn");
		pageNum.value=n;
		searchForm.submit();		
		
	})	
})


