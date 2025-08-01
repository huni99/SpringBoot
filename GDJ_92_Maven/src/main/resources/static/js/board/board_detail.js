const btn_cls = document.getElementsByClassName("action");
const frm = document.getElementById("frm");
for(el of btn_cls){
	el.addEventListener("click",function(e){
	/*	const kind = this.getAttribute("data-kind");
			console.log(this.dataset.kind);
			console.log(kind);*/
			
		let k = e.target;
		let kind = k.getAttribute("data-kind");
		if(kind=='d'){
			frm.setAttribute("method","post");
			frm.setAttribute("action","/notice/delete");
			frm.submit();
		}else{
			frm.setAttribute("action","/notice/update");
			frm.submit();
		}
		
	})
}
