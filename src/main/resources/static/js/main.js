document.addEventListener('DOMContentLoaded', () => {
    // For results.html - Displaying the tree JSON data
    if (window.location.pathname === "/results") {
        // Display tree passed from the controller in results.html
        const treeJson = /*[[${tree}]]*/ '{}'; // This will be injected by Thymeleaf into the JS
        document.getElementById('treeResults').textContent = JSON.stringify(JSON.parse(treeJson), null, 2);
    }

    // For previous-trees.html - Fetch and display previously saved trees
    if (window.location.pathname === "/previous-trees") {
        fetch('/previous-trees-data')  // Your backend endpoint to get the list of trees
            .then(response => response.json())
            .then(trees => {
                const list = document.getElementById('previousTreesList');
                trees.forEach(tree => {
                    const listItem = document.createElement('li');
                    listItem.textContent = `Tree ID: ${tree.id}, Numbers: ${tree.numbers}`;
                    list.appendChild(listItem);
                });
            });
    }
});

