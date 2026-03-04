// Dumax Trading Company JavaScript

// Smooth scrolling for navigation links
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
        e.preventDefault();
        const target = document.querySelector(this.getAttribute('href'));
        if (target) {
            target.scrollIntoView({
                behavior: 'smooth',
                block: 'start'
            });
        }
    });
});

// Scroll animation for fade-in elements
const observerOptions = {
    threshold: 0.1,
    rootMargin: '0px 0px -50px 0px'
};

const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
        if (entry.isIntersecting) {
            entry.target.classList.add('visible');
        }
    });
}, observerOptions);

document.querySelectorAll('.fade-in').forEach(el => {
    observer.observe(el);
});

// Color visualizer functionality
const colorOptions = document.querySelectorAll('.color-option');
const colorOverlay = document.getElementById('colorOverlay');

colorOptions.forEach(option => {
    option.addEventListener('click', function() {
        colorOptions.forEach(opt => opt.classList.remove('active'));
        this.classList.add('active');
        const color = this.getAttribute('data-color');
        colorOverlay.style.background = color;
    });
});

// Form submission handler
function handleSubmit(event) {
    event.preventDefault();
    const formData = new FormData(event.target);
    const data = Object.fromEntries(formData);

    alert(`Thank you, ${data.name}! We've received your quote request and will contact you soon at ${data.email}.`);
    event.target.reset();
}

// Navigation scroll effect
let lastScroll = 0;
const nav = document.querySelector('nav');

window.addEventListener('scroll', () => {
    const currentScroll = window.pageYOffset;

    if (currentScroll > 100) {
        nav.style.padding = '0.5rem 2rem';
    } else {
        nav.style.padding = '1rem 2rem';
    }

    lastScroll = currentScroll;
});

// FAQ accordion functionality
document.querySelectorAll('.faq-question').forEach(question => {
    question.addEventListener('click', function() {
        const faqItem = this.parentElement;
        const answer = this.nextElementSibling;
        const icon = this.querySelector('span:last-child');
        const isOpen = answer.style.maxHeight && answer.style.maxHeight !== '0px';

        // Close all other FAQs
        document.querySelectorAll('.faq-answer').forEach(ans => {
            ans.style.maxHeight = '0';
            ans.style.padding = '0 2rem';
        });
        document.querySelectorAll('.faq-question span:last-child').forEach(ic => {
            ic.textContent = '+';
            ic.style.transform = 'rotate(0deg)';
        });

        // Toggle current FAQ
        if (!isOpen) {
            answer.style.maxHeight = answer.scrollHeight + 'px';
            answer.style.padding = '0 2rem';
            icon.textContent = '−';
            icon.style.transform = 'rotate(180deg)';
        }
    });
});

// Add hover effect to help cards
document.querySelectorAll('.help-card').forEach(card => {
    card.addEventListener('mouseenter', function() {
        this.style.transform = 'translateY(-5px)';
    });
    card.addEventListener('mouseleave', function() {
        this.style.transform = 'translateY(0)';
    });
});
