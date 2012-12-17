package fr.todooz.web.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.joda.time.DateMidnight;
import org.joda.time.Interval;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.todooz.service.TagCloudService;
import fr.todooz.service.TaskService;
import fr.todooz.util.IntervalUtils;
import fr.todooz.util.TagCloud;

import fr.todooz.domain.Task;

@Controller
public class IndexController {
	@Inject
	private TaskService taskService;

	@Inject
	private TagCloudService tagCloudService;

	private Interval todayInterval() {
		DateMidnight today = new DateMidnight();

		return new Interval(today, today.plusDays(1));
	}

	@ModelAttribute
	public TagCloud tagCloud() {
		return tagCloudService.buildTagCloud();
	}

	@RequestMapping({ "/", "/index" })
	public String index(Model model) {
		model.addAttribute("tasks", taskService.findAll());
		return "index";
	}

	@RequestMapping("/search")
	public String search(String query, Model model) {
		model.addAttribute("tasks", taskService.findAll());
		return "index";

	}

	@RequestMapping("/tag/{tag}")
	public String tag(@PathVariable String tag, Model model) {
		model.addAttribute("tasks", taskService.findByTag(tag));
		return "index";
	}

	public String page(Model model, List<Task> tasks) {
		model.addAttribute("tasks", tasks);
		return "index";
	}

	@RequestMapping("/today")
	public String today(Model model) {
		return page(model,
				taskService.findByInterval(IntervalUtils.todayInterval()));
	}

	@RequestMapping("/tomorrow")
	public String tomorrow(Model model) {
		return page(model,
				taskService.findByInterval(IntervalUtils.tomorrowInterval()));
	}

	@PostConstruct
	public void bootstrap() {

		for (Task task : taskService.findAll()) {
			taskService.delete(task.getId());

		}
		if (taskService.count() == 0) {
			Task task1 = new Task();
			task1.setDate(new Date());
			task1.setTitle("Tache1");
			task1.setTags("poney");

			taskService.save(task1);

			Task task2 = new Task();
			task2.setDate(new Date());
			task2.setTitle("Tache2");
			task2.setTags("java");

			taskService.save(task2);

			Task task3 = new Task();
			task3.setDate(new Date());
			task3.setTitle("Read Tache3");
			task3.setTags("junit");

			taskService.save(task3);
		}
	}

}