package Source_Code.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import Source_Code.model.User;

@Service
public class MemberStore {

	private static List<User> store = new LinkedList<>();


	public List<User> getMembersList() {
		List<User> membersWithSerialId = new ArrayList<>();
		AtomicInteger serialId = new AtomicInteger(1);

		for (User user : store) {
			// Create a new User with a serialId and the same id and username
			User newUser = new User(user.id(), String.valueOf(serialId.getAndIncrement()), user.username());
			membersWithSerialId.add(newUser);
		}

		return membersWithSerialId;
	}


	public List<User> filterMemberListByUser(List<User> memberList, User user) {
		List<User> filteredList = new ArrayList<>();

		for (User filterUser : memberList) {
			if (!filterUser.id().equals(user.id())) {
				User newUser = new User(null, filterUser.serialId(), filterUser.username());
				filteredList.add(newUser);
			}
		}

		return filteredList;
	}


	public User getMember(String id) {
		return store.get(Integer.valueOf(id) - 1);
	}
	
	public void addMember(User member) {
		store.add(member);
	}
	
	public void removeMember(User member) {
		store.remove(member);
	}
}
