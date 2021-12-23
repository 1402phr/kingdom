document.getElementById('wgen').onclick = function() {
	if (wgen.checked) {
		document.getElementById('avtbox').classList.remove('dnone');
		document.getElementById('favt').classList.remove('dnone');
		document.getElementById('mavt').classList.add('dnone');
	}
}
document.getElementById('mgen').onclick = function() {
	if (mgen.checked) {
		document.getElementById('avtbox').classList.remove('dnone');
		document.getElementById('favt').classList.add('dnone');
		document.getElementById('mavt').classList.remove('dnone');
	}
}

document.getElementById('file').onchange = function(e){
	var pic = URL.createObjectURL(e.target.files[0]);
	var el = document.getElementById('img1');
	el.setAttribute('src', pic);
	el.classList.add('pd15');
	el.classList.add('w3-border');
	var clslist = el.classList.remove('dnone');
}