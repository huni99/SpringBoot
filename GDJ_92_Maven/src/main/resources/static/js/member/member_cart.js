const allCheck = document.getElementById("checkAll");
const check = document.querySelectorAll(".ch");
const deleteBtn = document.querySelector(".deleteBtn")
const Frm = document.getElementById("Frm")
const addBtn = document.querySelector(".addBtn");
allCheck.addEventListener("click", () => {
	check.forEach(c => {
		c.checked = allCheck.checked;
	})
})

check.forEach(c => {
	c.addEventListener("click", () => {
		let flag = true;
		check.forEach(ch => {
			flag = flag && ch.checked;
		})
		if (flag == true) {
			allCheck.checked = true;
		} else {
			allCheck.checked = false;
		}
	})
})
deleteBtn.addEventListener("click", () => {
	let arr = new Array();
	check.forEach(c => {
		if (c.checked) {
			arr.push(c.getAttribute("value"));
		}

	})
	if (arr.length == 0) {
		return;
	} else {
		for (let i = 0; i < arr.length; i++) {
			const ine = document.createElement("input");
			ine.setAttribute("type", "hidden");
			ine.setAttribute("name", "productNum");
			ine.setAttribute("value", arr[i]);

			Frm.appendChild(ine);
		}
		Frm.setAttribute("action", "./cartList");
		Frm.setAttribute("method", "post");
		Frm.submit();
	}
})

addBtn.addEventListener("click", () => {
	let arr = new Array();
	check.forEach(c => {
		if (c.checked) {
			arr.push(c.getAttribute("value"));
		}
	})
	if (arr.length == 0) {
		return;
	} else {
		for (let i = 0; i < arr.length; i++) {
			const ine = document.createElement("input");
			ine.setAttribute("type", "hidden");
			ine.setAttribute("name", "productNum");
			ine.setAttribute("value", arr[i]);
			Frm.appendChild(ine);
		}
		Frm.setAttribute("action", "/account/add");
		Frm.setAttribute("method", "post");
		Frm.submit();
	}

})