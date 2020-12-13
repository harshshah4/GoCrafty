
window.scrollSmooth = function(target) {
    var scrollContainer = target;
    do { //find scroll container
        scrollContainer = scrollContainer.parentNode;
        if (!scrollContainer) return;
        scrollContainer.scrollTop += 1;
    } while (scrollContainer.scrollTop == 0);

    var targetY = 0;
    do { //find the top of target relatively to the container
        if (target == scrollContainer) break;
        targetY += target.offsetTop;
    } while (target = target.offsetParent);

    scroll = function(c, a, b, i) {
        i++; if (i > 30) return;
        c.scrollTop = a + (b - a) / 30 * i;
        setTimeout(function(){ scroll(c, a, b, i); }, 20);
    }
    // start scrolling
    scroll(scrollContainer, scrollContainer.scrollTop, targetY, 0);
}

function showForm(){
	
	var deleteButton = document.getElementById("ppedit");
	if(deleteButton.style.visibility == "hidden"){
		deleteButton.style.visibility = "visible";
	}
}

function hideForm(){
	
	var deleteButton = document.getElementById("ppedit");
	if(deleteButton.style.visibility == "visible"){
		deleteButton.style.visibility = "hidden";
	}
	
	var hide = document.getElementById("deleteForm");
	if(hide.style.visibility == "visible"){
		hide.style.visibility = "hidden";
	}
}

var id = document.getElementById("uploadVideoButton");
var clicks = 0;

function showVideoUploadForm(){	
	
	clicks +=1;
	if(clicks==1){
		
		var form = document.createElement("form");
		form.setAttribute("method", "post"); 
		form.setAttribute("action", "modifyVideos");
    
		var vidName = document.createElement("input");
		vidName.setAttribute("type", "text"); 
		vidName.setAttribute("name", "videoName"); 
		vidName.setAttribute("placeholder", "Enter Video Name");
		vidName.setAttribute("required","true");
		
		var youtubeLink = document.createElement("input");
		youtubeLink.setAttribute("type", "text"); 
		youtubeLink.setAttribute("name", "youtubeLink"); 
		youtubeLink.setAttribute("placeholder", "Enter your Youtube link here");
		youtubeLink.setAttribute("required","true");
    
		var s = document.createElement("input"); 
		s.setAttribute("type", "submit"); 
		s.setAttribute("value", "Upload"); 
    
		form.appendChild(youtubeLink);
		form.appendChild(vidName);
		form.appendChild(s);
    
		document.getElementsByTagName("body")[0] 
		.appendChild(form); 
	}
	else{
		var h = document.createElement("H1")                
		var t = document.createTextNode("Hello World");     
		h.appendChild(t); 
	}
}

function showQuizUploadForm(){	
	
	clicks +=1;
	if(clicks==1){
		
		var form = document.createElement("form");
		form.setAttribute("method", "post"); 
		form.setAttribute("action", "modifyQuiz");
    
		var quizName = document.createElement("input");
		quizName.setAttribute("type", "text"); 
		quizName.setAttribute("name", "quizName"); 
		quizName.setAttribute("placeholder", "Enter Quiz Name");
		quizName.setAttribute("required","true");
		
		var docsLink = document.createElement("input");
		docsLink.setAttribute("type", "text"); 
		docsLink.setAttribute("name", "docsLink"); 
		docsLink.setAttribute("placeholder", "Enter quiz link here");
		docsLink.setAttribute("required","true");
		
		var respLink = document.createElement("input");
		respLink.setAttribute("type", "text"); 
		respLink.setAttribute("name", "respLink"); 
		respLink.setAttribute("placeholder", "Enter your response link here");
		respLink.setAttribute("required","true");
    
		var s = document.createElement("input"); 
		s.setAttribute("type", "submit"); 
		s.setAttribute("value", "Upload"); 
    
		form.appendChild(docsLink);
		form.appendChild(quizName);
		form.appendChild(respLink);
		form.appendChild(s);
    
		document.getElementsByTagName("body")[0] 
		.appendChild(form); 
	}
	else{
		var h = document.createElement("H1")                
		var t = document.createTextNode("Hello World");     
		h.appendChild(t); 
	}
}
