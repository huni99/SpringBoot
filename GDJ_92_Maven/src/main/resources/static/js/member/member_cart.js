const allCheck = document.getElementById("checkAll");
const check = document.querySelectorAll(".ch");

allCheck.addEventListener("click",()=>{
	check.forEach(c =>{
		c.checked = allCheck.checked;
		
	})
	
})