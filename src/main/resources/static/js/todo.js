/**document.addEventListener('DOMContentLoaded', function() {
           const searchInput = document.getElementById('searchInput');
           const rows = document.querySelectorAll('#interviewTable tbody tr');
           
           // Search functionality
           searchInput.addEventListener('input', function() {
               const searchTerm = this.value.toLowerCase();
               rows.forEach(row => {
                   const name = row.getAttribute('data-name');
                   const date = row.getAttribute('data-date').toLowerCase();
                   const matchesSearch = name.includes(searchTerm) || date.includes(searchTerm);
                   row.style.display = matchesSearch ? '' : 'none';
               });
           });
       });
	   
	   
	   **/
	  
	   document.addEventListener('DOMContentLoaded', function() {
	       const searchInput = document.getElementById('searchInput');
	       
	       if (searchInput) {
	           searchInput.addEventListener('input', function() {
	               const searchText = this.value.toLowerCase();
	               const rows = document.querySelectorAll('#interviewTable tbody tr');
	               
	               rows.forEach(row => {
	                   const name = row.getAttribute('data-name');
	                   const date = row.getAttribute('data-date');
	                   
	                   if (name.includes(searchText) || date.includes(searchText)) {
	                       row.style.display = '';
	                   } else {
	                       row.style.display = 'none';
	                   }
	               });
	           });
	       }

	       // Confirm before deletion
	       const deleteLinks = document.querySelectorAll('.delete-link');
	       deleteLinks.forEach(link => {
	           link.addEventListener('click', function(e) {
	               if (!confirm('Are you sure?')) {
	                   e.preventDefault();
	               }
	           });
	       });

	       // Navbar buttons functionality
	       const searchBtn = document.getElementById('searchBtn');
	       if (searchBtn) {
	           searchBtn.addEventListener('click', function() {
	               document.getElementById('searchInput').focus();
	           });
	       }
	   });