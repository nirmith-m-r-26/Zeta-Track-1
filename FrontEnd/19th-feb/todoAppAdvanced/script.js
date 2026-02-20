const taskInput = document.getElementById("taskInput");
const dueDateInput = document.getElementById("dueDate");
const addButton = document.getElementById("addButton");
const taskList = document.getElementById('taskList');

let tasks = JSON.parse(localStorage.getItem("tasks")) || [];
let currentFilter = "all";

function saveTasks(){
    localStorage.setItem("tasks", JSON.stringify(tasks));
}

addButton.addEventListener("click", ()=>{
    const text = taskInput.value.trim();
    const dueDate = dueDateInput.value;

    if(!text){
        alert("Task cannot be empty.");
        return;
    }

    tasks.push({
        id: Date.now(),
        text,
        dueDate,
        completed: false
    });

    taskInput.textContent = "";
    dueDateInput.textContent="";

    saveTasks();
    renderTasks();
});

function editTask(id){
    const newText = prompt("Edit Task: ");
    if(!newText) return;

    const task = tasks.find(t => t.id===id);
    task.text = newText;

    saveTasks();
    renderTasks();
}

function deleteTask(id){
    tasks = tasks.filter(t=>t.id!==id);
    saveTasks();
    renderTasks();
}

function toggleComplete(id){
    const task = tasks.find(t=>t.id===id);
    task.completed = !task.completed;
    saveTasks();
    renderTasks();
}

function setFilter(filter){
    currentFilter = filter;
    renderTasks();
}

function renderTasks(){
    taskList.innerHTML="";

    let filteredTasks = tasks;

    if(currentFilter === 'completed'){
        filteredTasks = tasks.filter(t=>t.completed);
    }else if(currentFilter === "pending"){
        filteredTasks = tasks.filter(t=> ! t.completed)
    }

    filteredTasks.forEach(task =>{
        const li = document.createElement("li");
        li.draggable = true;
        li.dataset.id = task.id;

        if(task.completed){
            li.classList.add("completed");
        }

        li.innerHTML=`
        ${task.text}
        <small>${task.dueDate || "No Date"}</small>
        <button onclick="toggleComplete(${task.id})">✅</button>
        <button onclick="editTask(${task.id})">Edit</button>
        <button onclick="deleteTask(${task.id})">delete</button>
        `;

        taskList.append(li);
    });

    enableDragDrop();
}

function enableDragDrop(){
    const items = document.querySelectorAll("#taskList li");
    
    items.forEach(item=>{
        item.addEventListener("dragstart", ()=>{
            item.classList.add("dragging");
        });
        item.addEventListener("dragstop", ()=>{
            item.classList.remove("dragging");
        });

    });

    taskList.addEventListener("dragover", event=>{
        event.preventDefault();
        const dragging = document.querySelector(".dragging");
        const afterElement = getDragAfterElement(taskList, event.clientY);

        if(afterElement == null){
            taskList.appendChild(dragging);
        }else{
            taskList.insertBefore(dragging, afterElement);
        }
    });
}

function getDragAfterElement(container, y) {
    const elements = [...container.querySelectorAll("li:not(.dragging)")];

    return elements.reduce((closest, child) => {
        const box = child.getBoundingClientRect();
        const offset = y - box.top - box.height / 2;

        if (offset < 0 && offset > closest.offset) {
            return { offset: offset, element: child };
        } else {
            return closest;
        }
    }, { offset: Number.NEGATIVE_INFINITY }).element;
}

function updateTaskOrder() {
    const ids = [...taskList.children].map(li => Number(li.dataset.id));
    tasks.sort((a, b) => ids.indexOf(a.id) - ids.indexOf(b.id));
    saveTasks();
}


renderTasks();