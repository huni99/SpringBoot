const btnAdd = document.querySelector("#add");
const result = document.querySelector("#result");
const de=document.querySelectorAll(".delete");
const deleteFile= document.querySelectorAll(".deleteFile");
let count = result.getAttribute("data-file-count");
btnAdd.addEventListener('click',()=>{
	if(count>4){
		alert("최대 5개 가능");
		return;
	}
	let div = document.createElement("div");//<div></div>
	div.classList.add("mb-3");//<div class="mb-3"></div>
	
	let ch = document.createElement("input");
	ch.setAttribute("type","file");
	ch.classList.add("form-control");
	ch.setAttribute("name","attaches");
	
	div.append(ch);
	
	ch=document.createElement("label");
	ch.setAttribute("for","Title");
	ch.classList.add("form-label");
	ch.innerText="FILE";
	ch.append
	
	ch = document.createElement("button");
	ch.setAttribute("type","button");
	ch.classList.add("delete");
	ch.innerText="X";
	
	div.append(ch);
	
	result.append(div);
	count++;
	

})
result.addEventListener('click',(e)=>{
	if(e.target.classList.contains("delete")){
		const parent = e.target.parentElement;
		parent.remove();
	}
	
})
deleteFile.forEach((f)=>{
	f.addEventListener('click',(e)=>{
		//fetch, axios
		let flag= confirm("삭제하겠음?");
		if(!flag){
			return;
		}
		let params = new URLSearchParams();
		params.append("fileNum",f.getAttribute("data-file-num"));
		
		fetch("./fileDelete",{
			method:"post",
			body:params
			})			
			.then(r=>r.text())
			.then(r=>{
				if(r.trim()==1){
					count--;
					f.remove();
				}else{
					alert("삭제 실패");
				}
		})
	})
})

